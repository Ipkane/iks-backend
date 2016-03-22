package com.iks.cms.core.exception;

import org.apache.commons.io.*;

/**
 * @author Igor Kaynov
 */
public class RequiredFieldError extends FieldError {
  public RequiredFieldError( String fieldName, String fieldLabel ) {
    super( fieldName, fieldLabel + " is required" );
  }
}
