'use strict';
angular.module( 'app.cms' )//
  .controller( 'GridEditController', GridEditController )
;//
function GridEditController( $scope, $log, payload, $uibModalInstance, CoreService ) {
  angular.extend( $scope, {
    alerts      : [],
    selectedItem: {}
  } );
  function init() {
    CoreService.getEditData( payload, function ( response ) {
      if ( response.isSuccess ) {
        $scope.selectedItem = response.success.item || {};
      } else {
        $log.error( response );
      }
    } );
  };
  $scope.save       = function ( isValid ) {
    //if (!isValid) {
    //  //$log.debug($scope.employeeForm);
    //  $scope.addAlert("Form is invalid");
    //  return;
    //}
    CoreService.updateEditData(
      angular.extend( angular.copy( payload ), { item: $scope.selectedItem } ),
      function ( response ) {
        if ( response.isSuccess ) {
          $uibModalInstance.close( $scope.selectedItem );
        } else {
          if ( response.failure.status == '400' ) {
            _.each(response.failure.error.errors, function(error) {
              $scope.addAlert(error.message);
            });
          } else {
            $scope.addAlert( response.failure.message );
          }
        }
      }, function ( response ) {
        $log.debug( response );
      }
    )
    ;
  };
  $scope.cancel     = function () {
    $uibModalInstance.dismiss( 'Cancel' );
  };
  $scope.addAlert   = function ( msg, params ) {
    $scope.alerts.push( {
      type   : 'danger',
      message: msg,
      params : params
    } )
  };
  $scope.closeAlert = function ( index ) {
    $scope.alerts.splice( index, 1 );
  };
  init();
}