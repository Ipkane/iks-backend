'use strict';
angular.module( 'app.cms' )//
  .controller( 'GridEditController', GridEditController )
;//
function GridEditController( $scope, $log, payload, $uibModalInstance, CoreService ) {
  angular.extend( $scope, {
    selectedItem: null
  } );
  function init() {
    CoreService.getEditData( payload, function ( response ) {
      if ( response.isSuccess ) {
        $scope.selectedItem = response.success.item;
      } else {
        $log.error( response );
      }
    } );
  };
  $scope.save   = function () {
    CoreService.updateEditData(
      angular.extend( angular.copy( payload ), { item: $scope.selectedItem } ),
      function ( response ) {
        if ( response.isSuccess ) {
          $uibModalInstance.close( $scope.selectedItem );
        } else {
          $log.error( response );
        }
      }, function ( response ) {
        $log.error( response );
      }
    )
    ;
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss( 'Cancel' );
  };
  init();
}