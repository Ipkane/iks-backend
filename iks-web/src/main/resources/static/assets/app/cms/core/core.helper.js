(function ( ng ) {
  'use strict';
  angular.module( 'app.cms' ) //
    .factory( 'GridHelper', GridHelper ) //
    .factory( '_', LodashService ) //
  ;
  function LodashService() {
    return _;
  }

  function GridHelper( CoreService, $q, $log ) {
    var grids = {};
    return {
      getGrid    : getGrid,
      getGridData: getGridData
    };
    function getGrid( name ) {
      var deferred = $q.defer();
      if ( !grids[ name ] ) {
        CoreService.getGrid( {
          appObj: 'employee'
        }, function ( response ) {
          if ( response.isSuccess ) {
            grids[ name ] = response.success.grid;
            deferred.resolve( grids[ name ] );
          } else {
            deferred.reject( response );
          }
        }, function ( response ) {
          deferred.reject( response );
        } );
      } else {
        deferred.resolve( grids[ name ] );
      }
      return deferred.promise;
    }

    function getGridData( request ) {
      return CoreService.getGridData( request ).$promise;
    }
  }
})( angular );