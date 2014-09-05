#!/bin/bash

# - Handle major deployment steps
grunt --force

# - Update the CSS files to replace url(/images with url(../images)
sed -i 's/url(\/imag/url(\.\.\/imag/g' dist/styles/*

# - Copy the cordova/magtek files to /dist
cp app/scripts/cordova/cordova_plugins.js dist/
cp -r app/scripts/cordova/plugins/ dist/plugins

# - Copy the cache.manifest to /dist
cp app/cache.manifest dist/cache.manifest
