(function ( ng ) {
  'use strict';
  var app = ng.module( 'app' );
  app.config( StateProviderConfiguration );
  function StateProviderConfiguration( $stateProvider, $urlRouterProvider ) {
    $stateProvider
      .state( 'home', {
        url        : '',
        templateUrl: 'assets/app/cms/home/home.html',
        controller : "HomeController"
      } )
      .state( 'employeeList', {
        url        : '',
        templateUrl: 'view/gridView?gridName=employee',
        controller : "EmployeeListController"
      }
    )
    ;
    $urlRouterProvider.otherwise( 'home' );
  }
})
( angular );
