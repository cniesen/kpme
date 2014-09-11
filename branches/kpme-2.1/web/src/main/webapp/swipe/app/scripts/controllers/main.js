/*
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
'use strict';

/**
 * The Main controller. This controller registers success and error call backs with the swipeService. It also exposes a 'selectPosition'
 * method to allow the user to physically select their desired position.
 */
angular.module('swipeApp').controller('MainCtrl', function($window, $scope, $interval, $sce, SwipeConstants, swipeService) {

	var multiplePositionsTimeout = undefined;
	var interval = undefined;

	/**
	 * UI View States
	 */
	var viewMode = {
		waiting : 'waiting',
		selectPosition : 'selectPosition',
		success : 'success',
		error : 'error'
	};

	/**
	 * time counter to select a position
	 */
	$scope.counter = SwipeConstants.Timer.MULTIPLE_POSITIONS / 1000;

	/**
	 * Define a safeApply wrapper to stop from causing: '$apply already in progress' errors
	 */
	$scope.safeApply = function(fn) {
		var phase = this.$root.$$phase;
		if (phase === '$apply' || phase === '$digest') {
			if (fn && (typeof (fn) === 'function')) {
				fn();
			}
		} else {
			this.$apply(fn);
		}
	};

	/**
	 * User has selected a position from the UI
	 */
	$scope.selectPosition = function(key) {
		stopMultiplePositionsTimeout();
		swipeService.selectPosition(key);
	};

	/**
	 * Starts the MULTIPLE_POSITIONS second timer to select a position, otherwise it force swipes in
	 */
	var startMultiplePositionsTimeout = function() {
		if (angular.isDefined(multiplePositionsTimeout)) {
			return;
		}

		$scope.counter = SwipeConstants.Timer.MULTIPLE_POSITIONS / 1000;
		multiplePositionsTimeout = $interval(function() {
			$scope.counter--;
		}, SwipeConstants.Timer.ONE_SECOND);
	};

	/**
	 * Stop the timer
	 */
	var stopMultiplePositionsTimeout = function() {
		if (angular.isDefined(multiplePositionsTimeout)) {
			$interval.cancel(multiplePositionsTimeout);
			multiplePositionsTimeout = undefined;
		}
	};

	/**
	 * Stop the internal interval from firing
	 */
	var stopInterval = function() {
		if (angular.isDefined(interval)) {
			$interval.cancel(interval);
			interval = undefined;
		}
	};

	/**
	 * Successful callback. This callback is fired as 'uiCallback' from within 'swipeService'
	 */
	var processSwipeResponse = function(data) {

		$scope.statusMessage = data.messages;

		switch (data.status) {
		// clock log successfully updated.
		case 'success':
			$scope.viewMode = viewMode.success;
			$scope.safeApply();

			stopInterval();
			interval = $interval(function() {
				$scope.viewMode = viewMode.waiting;
				$scope.safeApply();
			}, SwipeConstants.Timer.INTERVAL, 1);
			break;
		// multiple positions returned
		case 'multiple positions':
			$scope.viewMode = viewMode.selectPosition;
			$scope.positions = data.positions;
			$scope.safeApply();

			startMultiplePositionsTimeout();

			stopInterval();
			interval = $interval(function() {
				stopMultiplePositionsTimeout();
				$scope.selectPosition();
				$scope.safeApply();
			}, SwipeConstants.Timer.MULTIPLE_POSITIONS, 1);
			break;
		// error: server side error handler.
		case 'failure':
			$scope.viewMode = viewMode.error;
			$scope.safeApply();

			stopInterval();
			interval = $interval(function() {
				$scope.viewMode = viewMode.waiting;
				$scope.safeApply();
			}, SwipeConstants.Timer.INTERVAL, 1);
			break;
		}
	};

	/**
	 * Error callback. This callback is fired as 'uiErrorCallback' from within 'swipeService'
	 */
	var processSwipeError = function(data) {
		$scope.viewMode = viewMode.error;
		$scope.statusMessage = data.messages;
		$scope.safeApply();

		stopInterval();
		interval = $interval(function() {
			$scope.viewMode = viewMode.waiting;
			$scope.safeApply();
		}, SwipeConstants.Timer.INTERVAL, 1);
	};

	/**
	 * Init. Set defaults. Register UI callbacks with swipeService
	 */
	var init = function() {
		// init display screen
		$scope.viewMode = viewMode.waiting;

		// register UI callbacks
		swipeService.setUiSwipeCallback(processSwipeResponse);
		swipeService.setUiSwipeErrorCallback(processSwipeError);
	};

	init();
});