(function ( ng ) {
  'use strict';
  var app = ng.module( 'app' );
  app.config( StateProviderConfiguration );
  function StateProviderConfiguration( $stateProvider, $urlRouterProvider ) {
    $stateProvider
      .state( 'home', {
        url        : '/home',
        templateUrl: 'assets/app/cms/home/home.html',
        controller : "HomeController"
      } )
      .state( 'appObjListView', {
        url        : '/list/{appObj}',
        templateUrl: function ($stateParams){
          return 'view/listView?appObj=' + $stateParams.appObj;
        },
        controller : "AppObjListController as vm"
      } )
    ;
    $urlRouterProvider.otherwise( 'home' );
  }
})
( angular );
