package com.iks.cms.web.api.common;

import org.springframework.http.*;

public class ResponseEntityWithView< T > extends ResponseEntity< ViewEntityWrapper< T > > {
  public ResponseEntityWithView( HttpStatus statusCode ) {
    super( statusCode );
  }
  public ResponseEntityWithView( T body, HttpStatus statusCode ) {
    super( new ViewEntityWrapper< T >( body, JsonViews.Normal.class ), statusCode );
  }
  public ResponseEntityWithView( T body, Class< ? extends JsonViews.Normal > view, HttpStatus statusCode ) {
    super( new ViewEntityWrapper< T >( body, view ), statusCode );
  }
}
