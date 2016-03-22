(function ( ng ) {
  'use strict';
  angular.module( 'app.cms' ) //
    .factory( 'ApiService', ApiService ) //
    .factory( 'CoreService', CoreService ) //
  ;
  function CoreService( $resource, ApiService ) {
    return $resource( 'api/core/:action', {}, {
      getGrid       : ApiService.buildGetServiceMethod( 'getGrid' ),
      getGridData   : ApiService.buildPostServiceMethod( 'getGridData' ),
      getEditData   : ApiService.buildGetServiceMethod( 'getEditData' ),
      updateEditData: ApiService.buildPostServiceMethod( 'updateEditData' ),
      deleteItem    : ApiService.buildPostServiceMethod( 'deleteItem' )
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