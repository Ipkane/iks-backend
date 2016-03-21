package com.iks.cms.core.exception;

/**
 * @author Igor Kaynov
 */
public class FieldError implements IValidationError {
  private String fieldName;
  private String message;
  public FieldError( String fieldName, String message ) {
    this.fieldName = fieldName;
    this.message = message;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage( String message ) {
    this.message = message;
  }
  public String getFieldName() {
    return fieldName;
  }
  public void setFieldName( String fieldName ) {
    this.fieldName = fieldName;
  }
}
