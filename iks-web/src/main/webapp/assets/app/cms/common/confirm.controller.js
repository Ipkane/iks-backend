'use strict';
angular.module( 'app.cms' )//
  .controller( 'ConfirmModalController', ConfirmModalController )
;//
function ConfirmModalController( $scope, $log, title, message, onConfirm, $uibModalInstance ) {
  angular.extend($scope, {
    title: title,
    message: message
  });
  $scope.confirm   = function () {
    onConfirm().then(function(response) {
      if ( response.isSuccess ) {
        $uibModalInstance.close( response );
      } else {
        $log.error( response );
      }
    }, function ( response ) {
      $log.error( response );
    });
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss( 'Cancel' );
  };
}