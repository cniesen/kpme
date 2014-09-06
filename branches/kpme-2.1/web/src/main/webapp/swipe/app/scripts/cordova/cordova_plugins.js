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
cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/com.bentomobility.magtek/www/magtek.js",
        "id": "com.bentomobility.magtek.MagTek",
        "clobbers": [
            "magtek"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.bentomobility.magtek": "1.0"
};
// BOTTOM OF METADATA
});