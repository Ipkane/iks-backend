(function ( ng ) {
  'use strict';
  angular.module( 'app.cms' ) //
    .factory( 'ApiService', ApiService ) //
    .factory( 'CoreService', CoreService ) //
  ;
  function CoreService( $resource, ApiService ) {
    return $resource( 'core/:action', {}, {
      getGrid: ApiService.buildGetServiceMethod( 'getGrid' )
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