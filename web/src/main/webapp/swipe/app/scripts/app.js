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

/**
 * Register the 'swipeApp' with Angular
 */
var app = angular.module('swipeApp', [ 'ngCookies', 'ngResource', 'ngSanitize', 'ngRoute', 'LocalStorageModule' ]);

/**
 * Configuration.
 */
app.config([ '$routeProvider', '$httpProvider', 'localStorageServiceProvider', function($routeProvider, $httpProvider, localStorageServiceProvider) {

	// Always direct the user to 'views/main.html'
	$routeProvider.when('/', {
		templateUrl : 'views/main.html',
		controller : 'MainCtrl'
	}).otherwise({
		redirectTo : '/'
	});

	localStorageServiceProvider.setPrefix('swipe');
} ]);

/**
 * Define global end point URLs
 */
app.constant('SwipeConstants', {
	Endpoints : {
		HEART_BEAT : '/kpme-dev/remoting/mobileClockLogService/heartbeat',
		CLOCK_LOG : '/kpme-dev/remoting/mobileClockLogService/addClockLog'
	},
	Connection : {
		// Integer
		MAX_REQUESTS : 3,
		// Milliseconds
		MAX_TIMEOUT : 5000,
		// Milliseconds
		RECONNECT : 10000,
		// Milliseconds
		FLUSH_QUEUE : 30000
	},
	Storage : {
		QUEUE : 'clockLogQueue',
		SWIPE : 'swipe'
	},
	Timer : {
		// Milliseconds
		INTERVAL : 5000,
		// Milliseconds
		ONE_SECOND : 1000,
		// Milliseconds
		MULTIPLE_POSITIONS : 20000
	}
});
