package com.iks.cms.web.api.grid;

import com.fasterxml.jackson.annotation.*;
import com.iks.cms.core.exception.*;
import com.iks.cms.web.api.common.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ResponseValidationException extends AbstractApiResponse {
  @JsonIgnore
  private ValidationException exception;
  public ResponseValidationException( ValidationException exception ) {
    this.exception = exception;
  }
  public List< IValidationError > getErrors() {
    return exception.getErrors();
  }
}
