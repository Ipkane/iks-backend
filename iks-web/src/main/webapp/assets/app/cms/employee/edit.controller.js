'use strict';
angular.module( 'app.cms' )//
  .controller( 'GridEditController', GridEditController )
;//
function GridEditController( $scope, $log, $uibModalInstance, selectedItem, CoreService ) {
  angular.extend( $scope, {
    selectedItem: selectedItem
  } );
  $scope.save   = function () {
    CoreService.updateGridData( { item: selectedItem }, function ( response ) {
      if ( response.isSuccess ) {
        $uibModalInstance.close( $scope.selectedItem );
      } else {
        $log.error( response );
      }
    }, function ( response ) {
      $log.error( response );
    } );
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss( 'Cancel' );
  }
}