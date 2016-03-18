(function ( ng ) {
  'use strict';
  angular.module( 'app.cms' ) //
    .factory( 'ApiService', ApiService ) //
    .factory( 'CoreService', CoreService ) //
  ;
  function CoreService( $resource, ApiService ) {
    return $resource( 'api/core/:action', {}, {
      getGrid       : ApiService.buildGetServiceMethod( 'getGrid' ),
      getGridData   : ApiService.buildGetServiceMethod( 'getGridData' ),
      updateGridData: ApiService.buildPostServiceMethod( 'updateGridData' )
    } );
  }

  function ApiService() {
    return {
      buildGetServiceMethod : buildGetServiceMethod,
      buildPostServiceMethod: buildPostServiceMethod
    };
    function buildGetServiceMethod( action ) {
      return {
        method: 'GET',
        params: {
          action: action
        }
      }
    }

    function buildPostServiceMethod( action ) {
      return {
        method: 'POST',
        params: {
          action: action
        }
      }
    }
  };
})( angular );