(function () {
  'use strict';
  angular.module( 'app.cms', [] );
  angular.module( 'app', [//
    'ngRoute'
    , 'ngCookies' //
    , 'ngResource'
    , 'ui.router'
    , 'ui.bootstrap'
    , 'ngMessages' //
    , 'ngSanitize' //
    , 'app.cms'
  ] );
})();