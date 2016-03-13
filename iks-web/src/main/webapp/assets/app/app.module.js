(function () {
  'use strict';
  angular.module( 'app.cms', [] );
  angular.module( 'app', [//
    'ngRoute'
    , 'ngCookies' //
    , 'ui.router'
    , 'ngMessages' //
    , 'ngSanitize' //
    , 'app.cms'
  ] );
})();