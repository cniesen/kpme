/*
 * Copyright 2004-2015 The Kuali Foundation
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

describe('Swipe Services', function() {

//	/**
//	 * Define Global 'mockWindow' to provide temporary hook-ins to PhoneGap Cordova library
//	 */
//	var mockWindow = {};
//	mockWindow.registerPhoneGapCallback = function(onSuccess, onError) {
//		mockWindow.phoneGapCallbacks = {
//			success : onSuccess,
//			error : onError
//		};
//	};
//
//	mockWindow.swipe = function(string) {
//		mockWindow.phoneGapCallbacks.success(string);
//	};
//
//	mockWindow.swipeError = function(error) {
//		mockWindow.phoneGapCallbacks.error(error);
//	};
//
//	/**
//	 * Define app
//	 */
//	beforeEach(module('swipeApp'));
//
//	/**
//	 * Card swipe testing
//	 */
//	describe('Testing: Card Swipe Interaction', function() {
//		var dummyResponses = {
//			success : {
//				status : 0,
//				message : 'success'
//			},
//			selectPosition : {
//				status : 1,
//				/*
//				 * AssignmentDescriptionKey : AssignmentDescription
//				 * 
//				 * AssignmentDescriptionKey - KpmeUtils.formatAssignmentKey(). AssignmentDescription -
//				 * AssignmentService.getAssignmentDescription().
//				 */
//				cardData : 'XXXXXXXXXX',
//				positions : {
//					'MU-MOH_1_1401_0' : 'WA1 MU-DEPT1 : $5.000000 Rcd 1 MU-DEPT1',
//					'MU-MOH_1_1402_0' : 'WA2 MU-DEPT2 : $5.000000 Rcd 2 MU-DEPT2'
//				}
//			},
//			clockInSuccess : {
//				status : 0,
//				message : 'You are now Clocked \'In\' at 3:23pm for position: \'Courtside Bar Tender\''
//			},
//			clockOutSuccess : {
//				status : 0,
//				message : 'You are now Clocked \'Out\' at 11:57am for position: \'Activities Directory\''
//			},
//			errorUnableToRead : {
//				status : 2,
//				message : 'Unable to read card data'
//			},
//			errorClockLogRuleBroken : {
//				status : 2,
//				message : 'You are not allowed to log in to this location'
//			}
//		};
//
//		/**
//		 * Declare Services
//		 */
//		var swipeService = undefined;
//		var $httpBackend = undefined;
//		var $window = undefined;
//		var ClockLogEndpoints = undefined;
//
//		/**
//		 * Override $window with mockWindow
//		 */
//		beforeEach(module({
//			'$window' : mockWindow
//		}));
//
//		/**
//		 * Inject services
//		 */
//		beforeEach(inject(function(_swipeService_, _$httpBackend_, _$window_, _ClockLogEndpoints_) {
//			swipeService = _swipeService_;
//			$httpBackend = _$httpBackend_;
//			$window = _$window_;
//			ClockLogEndpoints = _ClockLogEndpoints_;
//		}));
//
//		/**
//		 * Cleanup
//		 */
//		afterEach(function() {
//			$httpBackend.verifyNoOutstandingExpectation();
//			$httpBackend.verifyNoOutstandingRequest();
//		});
//
//		it('should call the callback on swipe', function() {
//			var called = false;
//			swipeService.setUiSwipeCallback(function(data) {
//				called = true;
//			});
//			$httpBackend.whenPOST(ClockLogEndpoints.CLOCK_LOG).respond(dummyResponses.success);
//			$window.swipe("foo");
//			$httpBackend.flush();
//
//			expect(called).toBe(true);
//		});
//
//		it('should post swipe to server', function() {
//			var responseData = null;
//			swipeService.setUiSwipeCallback(function(data) {
//				responseData = data;
//			});
//			$httpBackend.expectPOST(ClockLogEndpoints.CLOCK_LOG).respond(dummyResponses.success);
//			$window.swipe("abc");
//			$httpBackend.flush();
//
//			expect(responseData.status).toBe(0);
//		});
//
//		it('should receive multiple positions (2) to be selected', function() {
//			var responseData = null;
//			swipeService.setUiSwipeCallback(function(data) {
//				responseData = data;
//			});
//			$httpBackend.expectPOST(ClockLogEndpoints.CLOCK_LOG).respond(dummyResponses.selectPosition);
//			$window.swipe("abc");
//			$httpBackend.flush();
//
//			expect(responseData.status).toBe(1);
//			expect(_.keys(responseData.positions).length).toBe(2);
//		});
//
//		it('should receive successful Clock In to [Courtside Bar Tender]', function() {
//			var responseData = null;
//			swipeService.setUiSwipeCallback(function(data) {
//				responseData = data;
//			});
//			$httpBackend.expectPOST(ClockLogEndpoints.CLOCK_LOG).respond(dummyResponses.clockInSuccess);
//			$window.swipe("abc");
//			$httpBackend.flush();
//
//			expect(responseData.status).toBe(0);
//			expect(responseData.message).toBe('You are now Clocked \'In\' at 3:23pm for position: \'Courtside Bar Tender\'');
//		});
//	});
//
//	/**
//	 * Test ClockLogQueueService functionality
//	 */
//	describe('Testing: ClockLogQueueService', function() {
//
//		var dummyData = {
//			noPosition : {
//				cardData : 'XXXXXXXX',
//				clockLogTimestamp : Date.now(),
//				fromQueue : true
//			},
//			withPosition : {
//				cardData : 'XXXXXXXX',
//				clockLogTimestamp : Date.now(),
//				position : 'MU-MOH_1_1401_0',
//				fromQueue : true
//			}
//		};
//
//		/**
//		 * Declare Services
//		 */
//		var clockLogQueueService = undefined;
//
//		/**
//		 * Inject services
//		 */
//		beforeEach(inject(function(_clockLogQueueService_) {
//			clockLogQueueService = _clockLogQueueService_;
//		}));
//
//		it('should start the clockLogQueueService', function() {
//			clockLogQueueService.startClockLogQueueRunner();
//			expect(clockLogQueueService.isRunningClockLogQueueRunner()).toBe(true);
//		});
//
//		it('should start, then stop the clockLogQueueService', function() {
//			clockLogQueueService.startClockLogQueueRunner();
//			clockLogQueueService.stopClockLogQueueRunner();
//			expect(clockLogQueueService.isRunningClockLogQueueRunner()).toBe(false);
//		});
//
//		it('should have an empty clockLogQueue', function() {
//			expect(clockLogQueueService.clockLogQueue().length).toBe(0);
//		});
//
//		it('should push 1 record to clockLogQueue', function() {
//			expect(clockLogQueueService.clockLogQueue().length).toBe(0);
//			clockLogQueueService.clockLogQueuePush(dummyData.noPosition);
//			expect(clockLogQueueService.clockLogQueue().length).toBe(1);
//		});
//	});
});
