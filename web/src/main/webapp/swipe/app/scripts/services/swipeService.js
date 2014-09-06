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
 * Register 'swipeService' with 'swipeApp'
 */
angular.module('swipeApp').service('swipeService', function($window, $http, $q, SwipeConstants, cordovaInterfaceService, heartbeatService, clockLogQueueService) {

	var self = this;
	var uiCallback = undefined;
	var uiErrorCallback = undefined;

	// swipeService
	/**
	 * TEMPORARY - START  
	 */
	self.swipeDev = function(cardData){
	  swipeHandler(cardData);
	};
	/**
	 * TEMPORARY - END  
	 */

	
	/**
	 * Register successful card swipe callback for UI Controller
	 */
	self.setUiSwipeCallback = function(callback) {
		uiCallback = callback;
	};

	/**
	 * Register failed card swipe callback for UI Controller.
	 */
	self.setUiSwipeErrorCallback = function(callback) {
		uiErrorCallback = callback;
	};

	/**
	 * Wrap the server contact in a promise to allow for timeout limits and retry requests
	 */
	var sendSwipeDeferred = function(swipeData) {
		var requestCount = 0;
		var swipeResults = $q.defer();

		// wrap in function to call itself on error
		var sendSwipe = function() {
			// contact server with swipe
			$http.post(SwipeConstants.Endpoints.CLOCK_LOG, swipeData, {
				timeout : SwipeConstants.Connection.MAX_TIMEOUT
			}).success(function(data) {
				// on success resolve the deferred promise
				swipeResults.resolve(data);
			}).error(function(data) {
				// retry the connection 3 times before rejecting
				if (requestCount < SwipeConstants.Connection.MAX_REQUESTS) {
					// recursive call to itself
					sendSwipe();
					requestCount++;
				} else {
					swipeResults.reject(data);
				}
			});
		};
		sendSwipe();

		return swipeResults.promise;
	};

	/**
	 * User has selected a position to apply to the clock log. Push position to server and wait for response
	 */
	self.selectPosition = function(position) {

		var swipeData = clockLogQueueService.getCurrentSwipe();
		swipeData.position = position;

		if (!heartbeatService.checkHeartbeat() || position === undefined) {
			connectivityIssueHandler(swipeData);
		} else {

			sendSwipeDeferred(swipeData).then(function(data) {
				if (data.status === 'success') {
					swipeData = undefined;
					clockLogQueueService.removeCurrentSwipe();
					uiCallback(data);
				} else {
					// error recognized on server and returned to service.
					swipeData = undefined;
					clockLogQueueService.removeCurrentSwipe();
					uiErrorCallback(data);
				}
			}, function() {
				connectivityIssueHandler(swipeData);
			});
		}
	};

	/**
	 * Successful card swipe callback. Push swipe data to server and wait for a response
	 */
	var swipeHandler = function(cardSwipe) {

		var cardData = JSON.parse(cardSwipe);

		if (validateSwipe(cardData, swipeError)) {

			var swipeData = {
				cardData : {
					track : cardData.track2_encrypted,
					ksn : cardData.ksn
				},
				clockLogTimestamp : Date.now()
			};

			heartbeatService.checkHeartbeat().then(function() {
				// send the swipe to the server in a deferred promise wrapper
				sendSwipeDeferred(swipeData).then(function(data) {
					if (data.status === 'success') {
						clockLogQueueService.removeCurrentSwipe();
						uiCallback(data);
					} else if (data.status === 'multiple positions') {
						clockLogQueueService.setCurrentSwipe(swipeData);
						uiCallback(data);
					} else {
						swipeData = undefined;
						clockLogQueueService.removeCurrentSwipe();
						swipeError([ 'Swipe not recognized on server', 'Please swipe again.' ]);
					}
				}, function() {
					connectivityIssueHandler(swipeData);
				});
			}, function() {
				connectivityIssueHandler(swipeData);
			});
		}
	};

	/**
	 * Failed card swipe callback.
	 */
	var swipeError = function(error) {
		var data = {};
		if (Array.isArray(error)) {
			data = {
				messages : error
			};
		} else {
			data = {
				messages : [ 'Unable to read your card data.', 'Please swipe again.' ]
			};
		}

		uiErrorCallback(data);
	};

	/**
	 * 'Error' handler for when the user swipes during no connectivity.
	 */
	var connectivityIssueHandler = function(swipeData) {
		swipeData.fromQueue = true;
		clockLogQueueService.queuePush(swipeData);
		swipeData = undefined;
		clockLogQueueService.removeCurrentSwipe();

		var data = {
			status : 'success',
			messages : [ 'Your clock action was successfully updated.' ]
		};
		uiCallback(data);
	};

	var validateSwipe = function(cardData, errorCallback) {

		if (!angular.isObject(cardData)) {
			errorCallback([ 'Unable to read your card data.', 'Please swipe again.' ]);
			return false;
		}

		// Track Decode Status. Consists of three 2-byte hex values representing the decode status for
		// tracks 1, 2, and 3 (respectively from left to right).
		// Values are: 00 = Track OK 01 = Track read Error 02 = Track is Blank
		// We only care about track 2 being read successfully (00)
		if (0 !== parseInt(cardData.track_status.slice(2, 4), 16)) {
			errorCallback([ 'Unable to read your card data.', 'Please swipe again.' ]);
			return false;
		}

		// js month is 0-11, card expiration is 1-12
		var date = new Date();
		var currentMonth = date.getMonth() + 1;
		var currentYear = date.getFullYear().toString().slice(2);
		var expMonth = cardData.card_exp_date.slice(2);
		var expYear = cardData.card_exp_date.slice(0, 2);
		if (currentYear >= expYear && currentMonth >= expMonth) {
			errorCallback([ 'Your card has expired and is no longer valid.', 'Please contact your Administrator.' ]);
			return false;
		}

		return true;
	};

	/**
	 * Init. Register callbacks with PhoneGap Cordova library.
	 */
	var init = function() {
		// Phone Gap API
		cordovaInterfaceService.initialize(swipeHandler, swipeError);
	};

	// start
	init();
});