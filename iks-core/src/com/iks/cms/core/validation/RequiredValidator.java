package com.iks.cms.core.validation;

import com.iks.cms.core.data.*;
import com.iks.cms.core.exception.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;

/**
 * @author Igor Kaynov
 */
public class RequiredValidator extends AbstractValidator {
  private String fieldName;
  public RequiredValidator( String fieldName ) {
    this.fieldName = fieldName;
  }
  @Override
  public boolean validate( IDataModel model, IDataItem item ) {
    IDataField dataField = model.getField( fieldName );
    Object value = item.getFieldValue( fieldName );
    if( value == null || StringUtils.trimToNull( value.toString() ) == null ) {
      setError( new FieldError( dataField.getFieldName(), dataField.getLabel() + " is required" ) );
      return false;
    }
    return true;
  }
}
