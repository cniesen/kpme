'use strict';

/**
 * The heart beat service.
 * 
 * This service periodically checks for connectivity against the HEART_BEAT endpoint. Every time an action is taken, e.g. a user swipe, or
 * there are queued swipes, a heart beat check is fired. If the check is successful, continue with the action. If not, queue the action for
 * later.
 * 
 * The watcher 'watchHeartbeatConnection' is enabled during service initialization. If the heart beat is unable to connect to the HEART_BEAT
 * endpoint, the watcher begins a reconnect phase. During the reconnect phase, the service will attempt to contact the HEART_BEAT endpoint
 * with a max timeout of MAX_TIMEOUT every RECONNECT seconds.
 */
angular.module('swipeApp').service('heartbeatService', function($rootScope, $http, $q, $interval, SwipeConstants) {

	var self = this;
	var heartbeatRunner = null;
	var isConnected = false;

	self.isConnectedOverride = undefined;

	/**
	 * Check the heart beat end point. Connected is any response status of 200-299. Wrap the heart beat check in a promise so other code can
	 * depend on the correct asynchronous response
	 */
	self.checkHeartbeat = function() {

		// define the deferred response
		var heartbeat = $q.defer();

		if (self.isConnectedOverride) {
			isConnected = false;
			heartbeat.reject(isConnected);
		} else {
			$http.get(SwipeConstants.Endpoints.HEART_BEAT, {
				timeout : SwipeConstants.Connection.MAX_TIMEOUT
			}).success(function(data, status) {
				isConnected = (status >= 200 && status <= 299);
				heartbeat.resolve(isConnected);
			}).error(function(data, statuscl) {
				isConnected = false;
				heartbeat.reject(isConnected);
			});
		}

		// return self.isConnected as a promise
		return heartbeat.promise;
	};

	/**
	 * Start the heart beat reconnect phase. Attempt reconnection every 'RECONNECT'
	 * 
	 * @see SwipeConstants.Connection.RECONNECT
	 */
	self.startReconnectingHeartbeat = function() {
		if (angular.isDefined(heartbeatRunner)) {
			return;
		}
		// No heart beat found. Starting Reconnect Phase.
		heartbeatRunner = $interval(function() {
			self.checkHeartbeat();
		}, SwipeConstants.Connection.RECONNECT);
	};

	/**
	 * Stop the heart beat reconnect phase.
	 */
	self.stopReconnectingHeartbeat = function() {
		if (angular.isDefined(heartbeatRunner)) {
			// Heart beat re-established. Stopping Reconnect Phase.
			$interval.cancel(heartbeatRunner);
			heartbeatRunner = undefined;
		}
	};

	/**
	 * Check to see if the heart beat reconnect phase is running
	 */
	self.isHeartbeatRunning = function() {
		return angular.isDefined(heartbeatRunner);
	};

	/**
	 * Watch service that handles enabling/disabling the heart beat reconnection phase.
	 */
	var watchHeartbeatConnection = function() {
		$rootScope.$watch(function() {
			return isConnected;
		}, function(newVal) {
			// We have no connection to the heart beat end point. begin reconnect
			if (!newVal && !self.isHeartbeatRunning()) {
				self.startReconnectingHeartbeat();
			}
			// The heart beat has been re-established. Shut down the runner
			if (newVal && self.isHeartbeatRunning()) {
				self.stopReconnectingHeartbeat();
			}
		}, true);
	};

	/**
	 * Init. Start up the Heart beat watcher.
	 */
	var init = function() {
		// enable the heart beat connection watch service
		watchHeartbeatConnection();
	};

	init();
});