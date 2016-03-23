package com.iks.cms.core.validation;

import com.iks.cms.core.exception.*;

/**
 * @author Igor Kaynov
 */
public abstract class AbstractValidator implements IValidator {
  private IValidationError error;
  public IValidationError getError() {
    return error;
  }
  public void setError( IValidationError error ) {
    this.error = error;
  }
}
