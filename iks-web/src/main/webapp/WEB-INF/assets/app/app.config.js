(function ( ng ) {
  var app = ng.module( 'app' );
  app.config( [ '$resourceProvider', function ( $resourceProvider ) {
    // Don't strip trailing slashes from calculated URLs
    $resourceProvider.defaults.stripTrailingSlashes = true;
  } ] );
})( angular );