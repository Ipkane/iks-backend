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
      .state( 'employeeList', {
        url        : '/employees',
        templateUrl: 'view/gridView?appObj=employee',
        controller : "AppObjListController"
      }
    )
    ;
    $urlRouterProvider.otherwise( 'home' );
  }
})
( angular );
