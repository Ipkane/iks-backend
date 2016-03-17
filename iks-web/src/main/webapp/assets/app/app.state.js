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
        templateUrl: 'assets/app/cms/employee/list.html',
        controller : "EmployeeListController"
      }
    )
    ;
    $urlRouterProvider.otherwise( 'home' );
  }
})
( angular );
