package com.iks.cms.web.utils;

import com.iks.cms.web.api.common.*;

import org.springframework.http.*;

/**
 * @author Igor Kaynov
 */
public class ApiUtils {
  public static ResponseEntity< DefaultResponseBody< ?, ? > > makeErrorResponse( String methodName, String message, AbstractApiRequest request ) {
    return new ResponseEntity< DefaultResponseBody< ?, ? > >( new DefaultResponseBody<>( methodName, request, HttpStatus.INTERNAL_SERVER_ERROR, message, message ), HttpStatus.OK );
  }
  public static ResponseEntityWithView< DefaultResponseBody< ?, ? > > makeErrorResponse_ExternalView( String methodName, String message, AbstractApiRequest request ) {
    return new ResponseEntityWithView< DefaultResponseBody< ?, ? > >( new DefaultResponseBody<>( methodName, request, HttpStatus.INTERNAL_SERVER_ERROR, message, message ), HttpStatus.OK );
  }
  public static ResponseEntityWithView< DefaultResponseBody< ?, ? > > makeClientErrorResponse_ExternalView( String methodName, String message, AbstractApiRequest request ) {
    return new ResponseEntityWithView< DefaultResponseBody< ?, ? > >( new DefaultResponseBody<>( methodName, request, HttpStatus.BAD_REQUEST, message, message ), HttpStatus.OK );
  }
  public static ResponseEntityWithView< DefaultResponseBody< ?, ? > > makeResponse_ExternalView( String methodName, AbstractApiRequest request, AbstractApiResponse response ) {
    return new ResponseEntityWithView< DefaultResponseBody< ?, ? > >( new DefaultResponseBody<>( methodName, request, response ), JsonViews.External.class, HttpStatus.OK );
  }
  public static ResponseEntity< DefaultResponseBody< ?, ? > > makeResponse( String methodName, AbstractApiRequest request, AbstractApiResponse response ) {
    return new ResponseEntity< DefaultResponseBody< ?, ? > >( new DefaultResponseBody<>( methodName, request, response ), HttpStatus.OK );
  }
}
