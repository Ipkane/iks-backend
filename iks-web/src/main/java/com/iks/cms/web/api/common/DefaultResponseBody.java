package com.iks.cms.web.api.common;

import com.fasterxml.jackson.annotation.*;

import org.springframework.http.*;

public class DefaultResponseBody< TRequest extends AbstractApiRequest, TResponse extends AbstractApiResponse > {
  private static final String VALUE_UNKNOWN = "unknown";
  @JsonIgnore
  private String     apiEndpoint;
  @JsonIgnore
  private TRequest   request;
  @JsonIgnore
  private TResponse  response;
  @JsonIgnore
  private HttpStatus status;
  @JsonIgnore
  private String     message;
  @JsonIgnore
  private String     developerMessage;
  @JsonIgnore
  private boolean success = true;
  public DefaultResponseBody( String apiEndpoint, TRequest request, TResponse response ) {
    this.apiEndpoint = apiEndpoint;
    this.request = request;
    this.response = response;
  }
  public DefaultResponseBody( String apiEndpoint, TRequest request, HttpStatus status, String message, String developerMessage ) {
    this.success = false;
    this.apiEndpoint = apiEndpoint;
    this.request = request;
    this.status = status;
    this.message = message;
    this.developerMessage = developerMessage;
  }
  @JsonProperty( "inResponseTo" )
  public CInResponseTo inResponseTo() {
    return new CInResponseTo( request, apiEndpoint );
  }
  @JsonProperty( "isSuccess" )
  public boolean isSuccess() {
    return success;
  }
  @JsonProperty( "success" )
  public TResponse success() {
    return response;
  }
  @JsonProperty( "failure" )
  public ErrorInfo failure() {
    return isSuccess() ? null : new ErrorInfo( status, message, developerMessage );
  }
  @SuppressWarnings( "unused" )
  private class CInResponseTo {
    private TRequest request;
    @JsonProperty( "method" )
    private String   apiEndpoint;
    CInResponseTo( TRequest request, String apiEndpoint ) {
      this.request = request;
      this.apiEndpoint = apiEndpoint;
    }
    public TRequest getRequest() {
      return request;
    }
    public String getApiEndpoint() {
      return apiEndpoint;
    }
  }
  @SuppressWarnings( "unused" )
  private class ErrorInfo {
    private HttpStatus status;
    private String     message;
    private String     developerMessage;
    ErrorInfo( HttpStatus status, String message, String developerMessage ) {
      this.status = status;
      this.message = message;
      this.developerMessage = developerMessage;
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
    public String getDeveloperMessage() {
      return developerMessage;
    }
  }
}
