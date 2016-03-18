(function ( ng ) {
  'use strict';
  angular.module( 'app.cms' )//
    .controller( 'EmployeeListController', EmployeeListController )
  ;//
  function EmployeeListController( $scope, $log, $timeout, CoreService, GridHelper ) {
    angular.extend( $scope, {
      grid : null,
      items: []
    } );
    function init() {
      if (angular.isString($scope.grid)) {
        $scope.grid = angular.fromJson($scope.grid);
      }
      GridHelper.getGridData( 'employee' ).then( function ( response ) {
        $scope.items = response.success.items;
      } ).catch( function ( response ) {
        $log.debug( response );
      } );
    }
    $timeout(init, 0);
  }
})( angular );