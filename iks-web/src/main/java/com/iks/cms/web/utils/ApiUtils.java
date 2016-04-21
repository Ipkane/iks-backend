package com.iks.cms.web.utils;

import com.iks.cms.web.api.common.*;

import org.springframework.http.*;

/**
 * @author Igor Kaynov
 */
public class ApiUtils {
//  public static ResponseEntity< DefaultResponseBody< ?, ? > > makeErrorResponse( String methodName, String message, AbstractApiRequest request ) {
//    return new ResponseEntity<>( new DefaultResponseBody<>( methodName, request, HttpStatus.INTERNAL_SERVER_ERROR, message, null ), HttpStatus.OK );
//  }
  public static ResponseEntity< DefaultResponseBody< ?, ? > > makeErrorResponse( String message, Exception ex ) {
    return new ResponseEntity<>( new DefaultResponseBody<>( HttpStatus.INTERNAL_SERVER_ERROR, message, new ExceptionResponse( ex ) ), HttpStatus.OK );
  }
  public static ResponseEntity< DefaultResponseBody< ?, ? > > makeClientErrorResponse( String message, AbstractApiResponse error ) {
    return new ResponseEntity<>( new DefaultResponseBody<>( HttpStatus.BAD_REQUEST, message, error ), HttpStatus.OK );
  }
  public static ResponseEntityWithView< DefaultResponseBody< ?, ? > > makeErrorResponse_ExternalView( String message, AbstractApiRequest request ) {
    return new ResponseEntityWithView<>( new DefaultResponseBody<>( HttpStatus.INTERNAL_SERVER_ERROR, message, null ), HttpStatus.OK );
  }
  public static ResponseEntityWithView< DefaultResponseBody< ?, ? > > makeClientErrorResponse_ExternalView( String message, AbstractApiResponse error, AbstractApiRequest request ) {
    return new ResponseEntityWithView<>( new DefaultResponseBody<>( HttpStatus.BAD_REQUEST, message, error ), HttpStatus.OK );
  }
  public static ResponseEntityWithView< DefaultResponseBody< ?, ? > > makeResponse_ExternalView( AbstractApiResponse response ) {
    return new ResponseEntityWithView<>( new DefaultResponseBody<>( response ), JsonViews.External.class, HttpStatus.OK );
  }
  public static ResponseEntity< DefaultResponseBody< ?, ? > > makeResponse( AbstractApiResponse response ) {
    return new ResponseEntity<>( new DefaultResponseBody<>( response ), HttpStatus.OK );
  }
}
