package com.iks.cms.web.api.common;

import com.fasterxml.jackson.annotation.*;

import org.springframework.http.*;

public class DefaultResponseBody< TRequest extends AbstractApiRequest, TResponse extends AbstractApiResponse > {
  private static final String VALUE_UNKNOWN = "unknown";
  @JsonIgnore
  private TResponse  response;
  @JsonIgnore
  private HttpStatus status;
  @JsonIgnore
  private String     message;
  @JsonIgnore
  private AbstractApiResponse     error;
  @JsonIgnore
  private boolean success = true;
  public DefaultResponseBody( TResponse response ) {
    this.response = response;
  }
  public DefaultResponseBody( HttpStatus status, String message, AbstractApiResponse error ) {
    this.success = false;
    this.status = status;
    this.message = message;
    this.error = error;
  }
  @JsonProperty( "success" )
  public boolean isSuccess() {
    return success;
  }
  @JsonProperty( "data" )
  public TResponse success() {
    return response;
  }

  @SuppressWarnings( "unused" )
  private class ErrorInfo {
    private HttpStatus          status;
    private String              message;
    private AbstractApiResponse error;
    ErrorInfo( HttpStatus status, String message, AbstractApiResponse error ) {
      this.status = status;
      this.message = message;
      this.error = error;
    }
    public String getStatus() {
      return status == null ? VALUE_UNKNOWN : status.toString();
    }
    public String getReason() {
      return status == null ? VALUE_UNKNOWN : status.getReasonPhrase();
    }
    public String getMessage() {
      return message;
    }
    public AbstractApiResponse getError() {
      return error;
    }
  }
}
