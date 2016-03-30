package com.iks.cms.web.api.common;

/**
 * @author Igor Kaynov
 */
public class ExceptionResponse extends AbstractApiResponse{
  private Exception e;
  public ExceptionResponse(Exception e) {
    this.e = e;
  }
  public String getException() {
    return e.toString();
  }
  public String getMessage() {
    return e.getCause().toString();
  }
}
