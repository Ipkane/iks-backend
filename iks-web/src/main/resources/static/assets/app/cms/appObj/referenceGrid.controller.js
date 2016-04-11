'use strict';
angular.module('app.cms')//
    .controller('ReferenceGridModalController', ReferenceGridModalController)
;//
/**
 * Modal with reference grid
 */
function ReferenceGridModalController($scope, $log, $windowInstance, $timeout, $rootScope, payload, CoreService, GridHelper, ModalHelper, _) {
  var vm = this;
  angular.extend($scope, {
    parentGridId: payload.parentGridId,
    parentItemId: payload.parentItemId,
    childGridId: null,
    childGridScope: null
  });
  function init() {
    $scope.childGridScope = angular.element($('#' + $scope.childGridId)).scope();
  }

  $scope.ok = function () {
    $windowInstance.close($scope.childGridScope.selectedItem);
  };
  $scope.cancel = function () {
    $windowInstance.dismiss('Cancel');
  };
  $timeout(init, 0);
}
