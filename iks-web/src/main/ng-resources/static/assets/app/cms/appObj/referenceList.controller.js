'use strict';
angular.module( 'app.cms' )//
  .controller( 'ReferenceListModalController', AppObjListController )
;//
/**
 * Modal with reference grid
 */
function AppObjListController( $scope, $log, $uibModalInstance, $timeout, $rootScope, payload, CoreService, GridHelper, ModalHelper, _ ) {
  var vm = this;
  angular.extend( $scope, {
    parentGridId  : payload.parentGridId,
    parentItemId  : payload.parentItemId,
    childGridId   : null,
    childGridScope: null
  } );
  function init() {
    $scope.childGridScope = angular.element( $( '#' + $scope.childGridId ) ).scope();
  }

  $scope.ok     = function () {
    CoreService.addGridItem( {
      gridId      : $scope.parentGridId,
      parentItemId: $scope.parentItemId,
      itemId      : $scope.childGridScope.selectedItem.id
    }, function ( response ) {
      if ( response.isSuccess ) {
        $uibModalInstance.close( $scope.childGridScope.selectedItem );
      } else {
        ModalHelper.showErrorModal( response );
      }
    }, function ( response ) {
      ModalHelper.showErrorModal( response );
    } );
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss( 'Cancel' );
  };
  $timeout( init, 0 );
}
