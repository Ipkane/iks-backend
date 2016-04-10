'use strict';
angular.module('app.cms')//
    .controller('ReferenceListModalController', ReferenceListModalController)
;//
/**
 * Modal with reference grid
 */
function ReferenceListModalController($scope, $log, $uibModalInstance, $timeout, $rootScope, payload, CoreService, GridHelper, ModalHelper, _) {
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
    $uibModalInstance.close($scope.childGridScope.selectedItem);
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss('Cancel');
  };
  $timeout(init, 0);
}
