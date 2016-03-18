'use strict';
angular.module( 'app.cms' )//
  .controller( 'GridEditController', GridEditController )
;//
function GridEditController( $scope, $uibModalInstance, selectedItem ) {
  angular.extend( $scope, {
    selectedItem: selectedItem
  } );
  $scope.save   = function () {
    $uibModalInstance.close($scope.selectedItem);
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss( 'Cancel' );
  }
}