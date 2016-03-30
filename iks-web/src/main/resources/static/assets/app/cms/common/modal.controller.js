'use strict';
angular.module( 'app.cms' )//
  .controller( 'ConfirmModalController', ConfirmModalController )
  .controller( 'ErrorModalController', ErrorModalController )
;//
function ConfirmModalController( $scope, $log, title, message, onConfirm, $uibModalInstance, ModalHelper ) {
  angular.extend($scope, {
    alerts      : [],
    title: title,
    message: message
  });
  $scope.confirm   = function () {
    $scope.alerts = [];
    onConfirm().then(function(response) {
      if ( response.isSuccess ) {
        $uibModalInstance.close( response );
      } else {
        $scope.addAlert(response.failure.message);
      }
    }, function ( response ) {
      ModalHelper.showErrorModal(response);
    });
  };
  $scope.cancel = function () {
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
}
function ErrorModalController( $scope, $log, response, $uibModalInstance ) {
  angular.extend($scope, {
    message: null
  });
  function init() {
    var failure = response.failure;
    $scope.message = failure.message;
    $scope.status = failure.status;
    $scope.reason = failure.reason;
    $scope.error = failure.error;
  }
  $scope.ok = function () {
    $uibModalInstance.close(  );
  };
  init();
}