'use strict';
angular.module( 'app.cms' )//
  .controller( 'EmployeeListController', EmployeeListController )
;//
function EmployeeListController( $scope, $log, $uibModal, $timeout, CoreService, GridHelper ) {
  angular.extend( $scope, {
    gridName    : null,
    grid        : null,
    selectedItem: null,
    items       : []
  } );
  function init() {
    if ( angular.isString( $scope.grid ) ) {
      $scope.grid = angular.fromJson( $scope.grid );
    }
    GridHelper.getGridData( $scope.gridName ).then( function ( response ) {
      $scope.items = response.success.items;
    } ).catch( function ( response ) {
      $log.debug( response );
    } );
  }

  $scope.selectItem    = function ( item ) {
    $scope.selectedItem = item;
  };
  $scope.openEditModal = function () {
    // open modal
    $uibModal.open(
      {
        animation   : true,
        templateUrl : 'view/gridEditView?gridName=' + $scope.gridName + "&itemId=" + $scope.selectedItem.id,
        controller  : 'GridEditController',
        controllerAs: 'vm',
        backdrop    : 'static',
        resolve     : {}
      }
    ).result.then( function ( response ) {
                   }, function () {
                     $log.info( 'Modal dismissed at: ' + new Date() );
                   } );
  };
  $timeout( init, 0 );
}
