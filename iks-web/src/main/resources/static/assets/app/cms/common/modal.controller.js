'use strict';
angular.module( 'app.cms' )//
  .controller( 'ConfirmModalController', ConfirmModalController )
  .controller( 'ErrorModalController', ErrorModalController )
;//
function ConfirmModalController( $scope, $log, title, message, onConfirm, $windowInstance, ModalHelper ) {
  angular.extend($scope, {
    title: title,
    message: message
  });
  $scope.confirm   = function () {
    $scope.alerts = [];
    onConfirm().then(function(response) {
      if ( response.isSuccess ) {
        $windowInstance.close( response );
      } else {
        ModalHelper.showErrorModal(response);
      }
    }, function ( response ) {
      ModalHelper.showErrorModal(response);
    });
  };
  $scope.cancel = function () {
    $windowInstance.dismiss( 'Cancel' );
  };
}
function ErrorModalController( $scope, $log, response, $windowInstance ) {
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
    $windowInstance.close(  );
  };
  init();
}