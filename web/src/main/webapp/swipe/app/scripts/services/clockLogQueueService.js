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
 * The clock log queue service
 * 
 * This service checks to see if the clock log queue has elements to flush to the server every FLUSH_QUEUE seconds. If the queue is empty,
 * no contact is made.
 * 
 * The queue maintained logged swipes in chronological order. When flushing, the service requests a heart beat check. If successful, the
 * flush begins. The flush will push each element to the server until a failure occurs. No more queued elements are pushed to the server
 * within this round of flushing.
 */
angular.module('swipeApp').service('clockLogQueueService', function($rootScope, $http, $q, $interval, SwipeConstants, heartbeatService, localStorageService) {

	var self = this;
	var clockLogQueueRunner = undefined;

	/**
	 * Return the 'clockLogQueue' from local storage
	 */
	self.clockLogQueue = function() {
		if (localStorageService.get(SwipeConstants.Storage.QUEUE) === null) {
			localStorageService.set(SwipeConstants.Storage.QUEUE, []);
		}
		return localStorageService.get(SwipeConstants.Storage.QUEUE);
	};

	/**
	 * Pushes a single object onto the back of the 'clockLogQueue' within local storage
	 */
	self.queuePush = function(data) {
		// add the swipe to the queue
		var queue = self.clockLogQueue();
		queue.push(data);
		localStorageService.set(SwipeConstants.Storage.QUEUE, queue);
	};

	/**
	 * Loops over the 'clockLogQueue' once within local storage and attempts to send each one to the server synchronously. Successful
	 * attempts are removed from the queue. A single failure will bar the rest of the queue from flushing.
	 */
	self.queueFlush = function(isConnected) {

		if (isConnected) {
			var valid = true;
			var queue = [];

			// deep copy the queue to allow element removal
			angular.copy(self.clockLogQueue(), queue);

			// for each element in 'clockLogQueue', push it to the server sequentially.
			// continue until an element fails, then stop pushing elements to server.
			// if successful, shift out the element from the copied array. When done,
			// Place the copy into storage as the new queue

			// initialize an empty list of sequentially executed promises
			var promise = $q.all(null);

			// for each swipe, if still valid, execute the promise and wait until it succeeds to proceed
			angular.forEach(self.clockLogQueue(), function(swipeData){
				if (valid) {
				    promise = promise.then(function(){
				    	return $http.post(SwipeConstants.Endpoints.CLOCK_LOG, swipeData).then(function(res){
				    		// success
							if ((res.data.status === 'success' || res.data.status === 'failure') && valid) {
								queue.shift();
								localStorageService.set(SwipeConstants.Storage.QUEUE, queue);
							} else {
								valid = false;
							}
				    	}, function(reason){
				    	  valid = false;
				    	});
				    });
				}
			});
		}
	};

	/**
	 * Check the server for connectivity via the heart beat end point every 5 minutes. If there is a heart beat response, check to see if
	 * there are any failed clock logs in the queue. If so, re-send them.
	 */
	self.startClockLogQueueRunner = function() {
		if (angular.isDefined(clockLogQueueRunner)) {
			return;
		}

		clockLogQueueRunner = $interval(function() {
			// If the queue has swipes, attempt to flush
			if (self.clockLogQueue().length > 0) {
				// only flush the queue if the heart beat check is successfully resolved
				heartbeatService.checkHeartbeat().then(function(isConnected) {
					self.queueFlush(isConnected);
				});
			}
		}, SwipeConstants.Connection.FLUSH_QUEUE);
	};

	/**
	 * Turn off the heart beat. Do not attempt to send any more queued clock logs
	 */
	self.stopClockLogQueueRunner = function() {
		if (angular.isDefined(clockLogQueueRunner)) {
			$interval.cancel(clockLogQueueRunner);
			clockLogQueueRunner = undefined;
		}
	};

	/**
	 * Test to see if the runner is active
	 */
	self.isRunningClockLogQueueRunner = function() {
		return angular.isDefined(clockLogQueueRunner);
	};

	/**
	 * Get the current swipe from local storage
	 */
	self.getCurrentSwipe = function() {
		return localStorageService.get(SwipeConstants.Storage.SWIPE);
	};

	/**
	 * Set the current swipe in local storage
	 */
	self.setCurrentSwipe = function(swipeData) {
		localStorageService.set(SwipeConstants.Storage.SWIPE, swipeData);
	};

	/**
	 * Remove the current swipe from local storage
	 */
	self.removeCurrentSwipe = function() {
		localStorageService.remove(SwipeConstants.Storage.SWIPE);
	};

	/**
	 * Init. Start up the Heart beat watcher. Start the ClockLogQueueRunner.
	 */
	var init = function() {
		// start the queueRunner
		self.startClockLogQueueRunner();
	};

	init();
});