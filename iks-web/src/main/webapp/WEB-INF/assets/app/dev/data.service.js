(function ( ng ) {
  'use strict';
  angular.module( 'app.dev' ) //
    .factory( 'DevService', DevService ) //
  ;
  function DevService( $resource, ApiService ) {
    return $resource( 'dev/:action', {}, {
      reloadAppObj: ApiService.buildPostServiceMethod( 'reloadAppObj' )
    } );
  }
})( angular );