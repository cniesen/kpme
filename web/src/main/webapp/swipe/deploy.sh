#!/bin/bash
#
# Copyright 2004-2015 The Kuali Foundation
#
# Licensed under the Educational Community License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.opensource.org/licenses/ecl2.php
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


# - Handle major deployment steps
grunt --force

# - Update the CSS files to replace url(/images with url(../images)
sed -i 's/url(\/imag/url(\.\.\/imag/g' dist/styles/*

# - Copy the cordova/magtek files to /dist
cp app/scripts/cordova/cordova_plugins.js dist/
cp -r app/scripts/cordova/plugins/ dist/plugins

# - Copy the cache.manifest to /dist
cp app/cache.manifest dist/cache.manifest
