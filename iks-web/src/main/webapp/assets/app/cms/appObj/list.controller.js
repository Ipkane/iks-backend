'use strict';
angular.module( 'app.cms' )//
  .controller( 'AppObjListController', AppObjListController )
;//
function EmployeeListController( $scope, $log, $uibModal, $timeout, CoreService, GridHelper, _ ) {
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
        templateUrl : 'view/gridEditView?appObj=' + $scope.gridName,
        controller  : 'GridEditController',
        controllerAs: 'vm',
        backdrop    : 'static',
        resolve     : {
          selectedItem: angular.copy( $scope.selectedItem )
        }
      }
    ).result.then( function ( updatedItem ) {
                     _.each( $scope.items, function ( item ) {
                       if ( item.id == updatedItem.id ) {
                         angular.extend( item, updatedItem );
                       }
                     } );
                     angular.extend( $scope.selectedItem, updatedItem );
                   }, function () {
                     $log.info( 'Modal dismissed at: ' + new Date() );
                   } );
  };
  $timeout( init, 0 );
}
