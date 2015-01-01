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
module.exports = function(grunt) {
  'use strict';

  // Load the grunt tasks
  require('load-grunt-tasks')(grunt);

  // Time the grunt tasks
  require('time-grunt')(grunt);

  grunt.initConfig({
    karma: {
      options: {
        autowatch: true,
        browsers: [
          'PhantomJS'
        ],
        configFile: 'test/karma.conf.js',
        reporters: ['dots'],
        singleRun: false
      },
      unit: {}
    },
    jshint: {
      grunt: {
        src: ['Gruntfile.js'],
        options: {
          node: true
        }
      },
      dev: {
        src: ['angular-local-storage.js'],
        options: {
          jshintrc: '.jshintrc',
        }
      },
      test: {
        src: ['test/spec/**/*.js'],
        options: {
          jshintrc: 'test/.jshintrc',
        }
      }
    },
    uglify: {
      dist: {
        files: {
          'angular-local-storage.min.js': 'angular-local-storage.js'
        }
      }
    }
  });

  grunt.registerTask('test', [
    'karma'
  ]);

  grunt.registerTask('default', [
    'jshint',
    'test'
  ]);

  grunt.registerTask('dist', [
    'uglify'
  ]);
};
