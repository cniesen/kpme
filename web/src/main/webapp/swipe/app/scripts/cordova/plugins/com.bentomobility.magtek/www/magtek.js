cordova.define("com.bentomobility.magtek.MagTek", function(require, exports, module) { var magtek = {
    openDevice: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'MagTek',
            'openDevice',
            []
        );
    },
    closeDevice: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'MagTek',
            'closeDevice',
            []
        );
    },
    listen: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'MagTek',
            'listen',
            []
        );
    }
};

module.exports = magtek;

});
