'use strict';
angular.module( 'app.cms' )//
  .controller( 'GridEditController', GridEditController )
;//
var formScope;
function GridEditController( $scope, $log, payload, $uibModalInstance, CoreService, ModalHelper ) {
  formScope = $scope;
  angular.extend( $scope, {
    itemId      : payload.itemId,
    appObj      : payload.appObj,
    alerts      : [],
    selectedItem: {}
  } );
  function init() {
    CoreService.getEditData( { appObj: payload.appObj, itemId: $scope.itemId }, function ( response ) {
      if ( response.isSuccess ) {
        $scope.selectedItem = response.success.item || {};
      } else {
        ModalHelper.showErrorModal( response );
      }
    }, function ( response ) {
      ModalHelper.showErrorModal( response );
    } );
  };
  $scope.save       = function ( isValid ) {
    //if (!isValid) {
    //  //$log.debug($scope.employeeForm);
    //  $scope.addAlert("Form is invalid");
    //  return;
    //}
    $scope.alerts = [];
    CoreService.updateEditData(
      { appObj: payload.appObj, item: $scope.selectedItem, isNew: payload.isNew },
      function ( response ) {
        if ( response.isSuccess ) {
          $uibModalInstance.close( $scope.selectedItem );
        } else {
          if ( response.failure.status == '400' ) {
            _.each( response.failure.error.errors, function ( error ) {
              $scope.addAlert( error.message );
            } );
          } else {
            ModalHelper.showErrorModal(response);
          }
        }
      }, function ( response ) {
        ModalHelper.showErrorModal(response);
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