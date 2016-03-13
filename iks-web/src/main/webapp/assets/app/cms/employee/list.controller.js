(function ( ng ) {
  'use strict'
  angular.module( 'app.cms' )//
    .controller( 'EmployeeListController', EmployeeListController )
  ;//
  function EmployeeListController( $scope, CoreService ) {
    function init() {
      var grid = CoreService.getGrid( {
        name: 'employee'
      } );
    }

    init();
  }
})( angular );