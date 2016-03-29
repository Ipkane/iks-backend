'use strict';
angular.module( 'app.cms' )//
  .controller( 'ReferenceListModalController', AppObjListController )
;//
/**
 * Modal with reference grid
 */
function AppObjListController( $scope, $log, $uibModalInstance, $timeout, $rootScope, payload, CoreService, GridHelper, _ ) {
  var vm = this;
  angular.extend( $scope, {
    parentGridId  : payload.gridId,
    parentItemId  : payload.parentItemId,
    childGridId   : null,
    childGridScope: null
  } );
  function init() {
    $scope.childGridScope = angular.element( $( '#' + $scope.childGridId ) ).scope();
    $log.debug($scope);
  }

  $scope.ok     = function () {
    CoreService.addGridItem( {
      gridId      : $scope.parentGridId,
      parentItemId: $scope.parentItemId,
      itemId      : $scope.childGridScope.selectedItem.id
    }, function ( response ) {
      if ( response.isSuccess ) {
        $uibModalInstance.close( $scope.childGridScope.selectedItem );
      }
    } );
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss( 'Cancel' );
  };
  $timeout( init, 0 );
}
