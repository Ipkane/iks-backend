package com.iks.cms.core.grid;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class DataRow implements IDataRow {
  private Map< String, Object > fieldValueMap = new LinkedHashMap<>();
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

}
