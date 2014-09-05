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
