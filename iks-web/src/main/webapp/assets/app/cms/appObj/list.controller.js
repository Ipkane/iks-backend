'use strict';
angular.module( 'app.cms' )//
  .controller( 'AppObjListController', AppObjListController )
;//
function AppObjListController( $scope, $log, $uibModal, $timeout, CoreService, GridHelper, _ ) {
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
    reload();
  }

  function reload() {
    GridHelper.getGridData( $scope.gridName ).then( function ( response ) {
      $scope.items          = response.success.items;
      var foundSelectedItem = false;
      if ( $scope.selectedItem ) {
        _.each( $scope.items, function ( item ) {
          if ( item.id == $scope.selectedItem.id ) {
            $scope.selectedItem = item;
            foundSelectedItem   = true;
          }
        } );
        if ( !foundSelectedItem ) {
          $scope.selectedItem = null;
        }
      }
    } ).catch( function ( response ) {
      $log.debug( response );
    } );
  };
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
          payload: { appObj: $scope.gridName, itemId: $scope.selectedItem.id }
        }
      }
    ).result.then( function ( updatedItem ) {
                     reload();
                   }, function () {
                     $log.info( 'Modal dismissed at: ' + new Date() );
                   } );
  };
  $timeout( init, 0 );
}
