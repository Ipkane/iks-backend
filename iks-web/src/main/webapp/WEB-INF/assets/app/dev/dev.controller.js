'use strict';
angular.module( 'app.dev' )//
  .controller( 'DevPanelController', DevPanelController )
;//
function DevPanelController( $scope, $log, DevService ) {
  angular.extend( $scope, {} );
  $scope.reloadAppObj = function () {
    DevService.reloadAppObj();
  }
}