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
 * The cordova interface service
 * 
 * This service acts as mapping to the iPad's Cordova/PhoneGap libraries. These libraries expose the Magtek iDynamo secured card reader for
 * use within the webapp.
 * 
 * The 'initialize' method will handle all of the setup needed to open and begin listening to the iDynamo card reader. See
 * 'swipeService.init()' to see how 'initialize' is called.
 */
angular.module('swipeApp').service('cordovaInterfaceService', function() {

	var self = this;

	var successHandler = undefined;
	var errorHandler = undefined;	

	/**
	 * Initialize the interaction between the Magtek iDynamo device, the Cordova/Phonegap layer, and the iPad
	 */
	self.initialize = function(success, error) {
		successHandler = success;
		errorHandler = error;

		self.bindEvents();
		self.listenerId = null;
		self.isListening = false;
		self.isOpen = false;
	};

	/**
	 * Bind the 'deviceready' event to fire off 'self.onDeviceReady'
	 */
	self.bindEvents = function() {
		document.addEventListener('deviceready', self.onDeviceReady, false);
	};

	/**
	 * Call 'self.receivedEvent'
	 */
	self.onDeviceReady = function() {
		self.receivedEvent('deviceready');
	};

	var magtek = null;
	/**
	 * Main entry method to enable interaction with the Magtek iDynamo
	 */
	self.receivedEvent = function(id) {
		magtek = cordova.require("com.bentomobility.magtek.MagTek");
		// Here you know the appDevice is ready to start listening.
		// Logic to open and start the magtek device could go here.
		self.openDevice();

		// reset the internal magtek listening id every 2 seconds
		self.listenerId = window.setInterval(function() {
			self.listen();
		}, 2000);
	};

	/**
	 * Open the iDynamo device and alert the user
	 */
	self.openDevice = function() {
		var success = function(message) {
			self.isListening = false;
			self.isOpen = true;
			alert('Device is ready!');
		};
		var error = function(message) {
			alert('Error: ' + message);
		};

		magtek.openDevice(success, error);
	};

	/**
	 * Close the iDynamo device and alert the user
	 */
	self.closeDevice = function() {
		var success = function(message) {
			appDevice.isListening = false;
			appDevice.isOpen = false;
			alert('Device is closed.');
		};
		var error = function(message) {
			alert('Error: ' + message);
		};

		magtek.closeDevice(success, error);
	};

	/**
	 * The iDynamo device can now speak with the javascript. Alert the user
	 */
	self.listen = function() {
		if (self.isOpen === false) {
			return;
		}

		if (self.isListening === false) {
			alert('Listening...');
		}

		self.isListening = true;

		magtek.listen(successHandler, errorHandler);
	};
});