package com.iks.cms.core.exception;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ValidationException extends Exception {
  public ValidationException(List< IValidationError > errors) {
    super();
    this.errors = errors;
  }
  private List< IValidationError > errors = new ArrayList<>();
  public List< IValidationError > getErrors() {
    return errors;
  }
  public void setErrors( List< IValidationError > errors ) {
    this.errors = errors;
  }
  public void addError(IValidationError error) {
    this.errors.add( error );
  }
}
