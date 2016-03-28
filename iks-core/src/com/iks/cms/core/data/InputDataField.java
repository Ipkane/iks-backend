package com.iks.cms.core.data;

import com.iks.cms.core.constant.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

import java.text.*;

/**
 * @author Igor Kaynov
 */
public class InputDataField extends SimpleDataField {
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
  @Override
  public void parse( Element xmlElement ) {
    super.parse( xmlElement );
    if( xmlElement.hasAttribute( ModelConstant.PRIMARY_KEY_ATTR ) ) {
      setIsPrimaryKey( BooleanUtils.toBoolean( xmlElement.getAttribute( ModelConstant.PRIMARY_KEY_ATTR ) ) );
    }
    if( xmlElement.hasAttribute( ModelConstant.TYPE_ATTR ) ) {
      setType( EDataType.getByValue( xmlElement.getAttribute( ModelConstant.TYPE_ATTR ) ) );
    }
  }
}
