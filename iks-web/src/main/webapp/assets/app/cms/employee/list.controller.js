(function ( ng ) {
  'use strict'
  angular.module( 'app.cms' )//
    .controller( 'EmployeeListController', EmployeeListController )
  ;//
  function EmployeeListController( $scope, CoreService ) {
    angular.extend($scope, {
      grid: null
    });
    function init() {
      CoreService.getGrid( {
        gridName: 'employee'
      }, function ( response ) {
        $scope.grid = response.data.grid;
        CoreService.getGridData({
          gridName: 'employee'
        })
      } );
    }

    init();
  }
})( angular );