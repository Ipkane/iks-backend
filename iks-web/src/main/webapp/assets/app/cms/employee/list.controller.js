(function ( ng ) {
  'use strict'
  angular.module( 'app.cms' )//
    .controller( 'EmployeeListController', EmployeeListController )
  ;//
  function EmployeeListController( $scope, $log, CoreService ) {
    angular.extend( $scope, {
      grid : null,
      items: [],
    } );
    function init() {
      CoreService.getGrid( {
        gridName: 'employee'
      } ).$promise.then( function ( response ) {
                           $scope.grid = response.success.grid;
                           return CoreService.getGridData( {
                             gridName: 'employee'
                           } ).$promise;
                         } ).then( function ( response ) {
                                     $scope.items = response.success.items;
                                   } ).catch( function ( response ) {
                                                $log.debug( response );
                                              } );
    }

    init();
  }
})( angular );