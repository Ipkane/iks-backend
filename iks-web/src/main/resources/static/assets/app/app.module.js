(function () {
  'use strict';
  angular.module('app.cms', []);
  angular.module('app.dev', []);
  angular.module('app', [//
    'ngRoute',
    //'ngCookies',
    'ngResource',
    'ui.router',
    //'ui.bootstrap',
    'ngMessages',
    'ngSanitize',
    'app.cms',
    'app.dev',
    'kendo.window',
    'kendo.directives'
  ]);
})();