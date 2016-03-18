(function ( ng ) {
  'use strict';
  angular.module( 'app.cms' ) //
    .factory( 'ApiService', ApiService ) //
    .factory( 'CoreService', CoreService ) //
  ;
  function CoreService( $resource, ApiService ) {
    return $resource( 'api/core/:action', {}, {
      getGrid: ApiService.buildGetServiceMethod( 'getGrid' ),
      getGridData: ApiService.buildGetServiceMethod( 'getGridData' )
    } );
  }
  function ApiService() {
    return {
      buildGetServiceMethod: buildGetServiceMethod
    };
    function buildGetServiceMethod( action ) {
      return {
        method: 'GET',
        params: {
          action: action
        }
      }
    }
  };
})( angular );