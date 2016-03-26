package com.iks.cms.core.grid;

import com.fasterxml.jackson.annotation.*;
import com.iks.cms.core.exception.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class DataItem implements IDataItem {
  @JsonInclude( JsonInclude.Include.NON_NULL )
  private Map< String, Object >    fieldValueMap = new LinkedHashMap<>();
  @JsonIgnore
  private List< IValidationError > errors        = new ArrayList<>();
  @JsonValue
  @JsonAnyGetter
  public Map< String, Object > getFieldValueMap() {
    return fieldValueMap;
  }
  public void setFieldValueMap( Map< String, Object > data ) {
    this.fieldValueMap = data;
  }
  @JsonAnySetter
  public void addFieldValue( String fieldName, Object value ) {
    fieldValueMap.put( fieldName, value );
  }
  @Override
  @JsonIgnore
  public Object getFieldValue( String fieldName ) {
    return fieldValueMap.get( fieldName );
  }
  @JsonIgnore
  public List< IValidationError > getErrors() {
    return errors;
  }
  public void setErrors( List< IValidationError > errors ) {
    this.errors = errors;
  }
  public void addError(IValidationError error) {
    errors.add( error );
  }
  public void cleanErrors() {
    errors.clear();
  };
}
