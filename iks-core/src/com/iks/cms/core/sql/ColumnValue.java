package com.iks.cms.core.sql;

import org.apache.commons.lang3.*;

/**
 * @author Igor Kaynov
 */
public class ColumnValue {
  private Object     value = null;
  private ColumnType type  = ColumnType.String;
  public ColumnValue( Object value ) {
    this.value = value;
  }
  public ColumnValue( Object value, ColumnType type ) {
    this( value );
    this.type = type;
  }
  public Object getValue() {
    return value;
  }
  public void setValue( Object value ) {
    this.value = value;
  }
  public String toString() {
    switch( type ) {
      case String:
        String valueStr = StringUtils.trimToNull( value.toString() );
        return valueStr == null ? null : "'" + valueStr + "'";
      default:
        return value.toString();
    }
  }
}
