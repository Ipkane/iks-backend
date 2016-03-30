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
  public static ResponseEntity< DefaultResponseBody< ?, ? > > makeErrorResponse( String methodName, String message, AbstractApiRequest request, Exception ex ) {
    return new ResponseEntity<>( new DefaultResponseBody<>( methodName, request, HttpStatus.INTERNAL_SERVER_ERROR, message, new ExceptionResponse( ex ) ), HttpStatus.OK );
  }
  public static ResponseEntity< DefaultResponseBody< ?, ? > > makeClientErrorResponse( String methodName, String message, AbstractApiResponse error, AbstractApiRequest request ) {
    return new ResponseEntity<>( new DefaultResponseBody<>( methodName, request, HttpStatus.BAD_REQUEST, message, error ), HttpStatus.OK );
  }
  public static ResponseEntityWithView< DefaultResponseBody< ?, ? > > makeErrorResponse_ExternalView( String methodName, String message, AbstractApiRequest request ) {
    return new ResponseEntityWithView<>( new DefaultResponseBody<>( methodName, request, HttpStatus.INTERNAL_SERVER_ERROR, message, null ), HttpStatus.OK );
  }
  public static ResponseEntityWithView< DefaultResponseBody< ?, ? > > makeClientErrorResponse_ExternalView( String methodName, String message, AbstractApiResponse error, AbstractApiRequest request ) {
    return new ResponseEntityWithView<>( new DefaultResponseBody<>( methodName, request, HttpStatus.BAD_REQUEST, message, error ), HttpStatus.OK );
  }
  public static ResponseEntityWithView< DefaultResponseBody< ?, ? > > makeResponse_ExternalView( String methodName, AbstractApiRequest request, AbstractApiResponse response ) {
    return new ResponseEntityWithView<>( new DefaultResponseBody<>( methodName, request, response ), JsonViews.External.class, HttpStatus.OK );
  }
  public static ResponseEntity< DefaultResponseBody< ?, ? > > makeResponse( String methodName, AbstractApiRequest request, AbstractApiResponse response ) {
    return new ResponseEntity<>( new DefaultResponseBody<>( methodName, request, response ), HttpStatus.OK );
  }
}
