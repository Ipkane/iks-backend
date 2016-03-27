package com.iks.cms.core.data;

import org.apache.commons.lang3.*;

/**
 * @author Igor Kaynov
 */
public class SimpleDataField extends AbstractDataField {
  private boolean isPrimaryKey = false;
  private EDataType          type       = EDataType.String;
  public boolean isPrimaryKey() {
    return isPrimaryKey;
  }
  public void setIsPrimaryKey( boolean isPrimaryKey ) {
    this.isPrimaryKey = isPrimaryKey;
  }
  @Override
  public Object parseValue( String value ) {
    switch( type ) {
      case String:
        return value;
      case Boolean:
        return BooleanUtils.toBoolean( value );
      default:
        return value;
    }
  }
  public EDataType getType() {
    return type;
  }
  public void setType( EDataType type ) {
    this.type = type;
  }
}
