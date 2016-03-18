'use strict';
angular.module( 'app.cms' )//
  .controller( 'GridEditController', GridEditController )
;//
function GridEditController( $scope, $uibModalInstance ) {
  $scope.save   = function () {
    $uibModalInstance.close();
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss('Cancel');
  }
}