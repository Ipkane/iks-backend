'use strict';
angular.module( 'app.cms' )//
  .controller( 'ConfirmModalController', ConfirmModalController )
  .controller( 'ErrorModalController', ErrorModalController )
;//
function ConfirmModalController( $scope, $log, title, message, onConfirm, $uibModalInstance, ModalHelper ) {
  angular.extend($scope, {
    title: title,
    message: message
  });
  $scope.confirm   = function () {
    $scope.alerts = [];
    onConfirm().then(function(response) {
      if ( response.isSuccess ) {
        $uibModalInstance.close( response );
      } else {
        ModalHelper.showErrorModal(response);
      }
    }, function ( response ) {
      ModalHelper.showErrorModal(response);
    });
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss( 'Cancel' );
  };
}
function ErrorModalController( $scope, $log, response, $uibModalInstance ) {
  angular.extend($scope, {
    message: null
  });
  function init() {
    var failure = response.failure;
    if (failure) {
      $scope.status    = failure.status;
      $scope.reason    = failure.reason;
      $scope.message   = failure.error.message;
      $scope.exception = failure.error.exception;
    } else {
      var data = response.data;
      $scope.status    = response.status;
      $scope.message   = data.message;
      $scope.reason    = data.error;
      $scope.exception = data.exception;
    }
  }
  $scope.ok = function () {
    $uibModalInstance.close(  );
  };
  init();
}