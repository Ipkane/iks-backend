(function ( ng ) {
  'use strict';
  angular.module( 'app.cms' )//
    .controller( 'EmployeeListController', EmployeeListController )
  ;//
  function EmployeeListController( $scope, $log, CoreService, GridHelper ) {
    angular.extend( $scope, {
      grid : null,
      items: []
    } );
    function init() {
      GridHelper.getGrid( 'employee' ).then( function ( grid ) {
        $scope.grid = grid;
        return GridHelper.getGridData('employee');
      } ).then( function ( response ) {
        $scope.items = response.success.items;
      } ).catch( function ( response ) {
        $log.debug( response );
      } );
    }

    init();
  }
})( angular );