'use strict';
angular.module( 'app.cms' )//
  .controller( 'ConfirmModalController', ConfirmModalController )
;//
function ConfirmModalController( $scope, $log, title, message, onConfirm, $uibModalInstance ) {
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
      $log.error( response );
      $scope.addAlert("Server error occured");
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