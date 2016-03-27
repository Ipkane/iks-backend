package com.iks.cms.core.data;

import org.thymeleaf.util.*;

/**
 * @author Igor Kaynov
 */
public enum EDataType {
  String( "string" ),
  Boolean( "boolean" ),
  Integer( "integer" ),
  Float( "float" );
  private String value;
  private EDataType( String value ) {
    this.value = value;
  }
  public String getValue() {
    return value;
  }
  public static EDataType getByValue( String value ) {
    String lowerCaseValue = value.toLowerCase();
    for( EDataType type : EDataType.values() ) {
      if( type.getValue().equals( lowerCaseValue ) ) {
        return type;
      }
    }
    return null;
  }
}
