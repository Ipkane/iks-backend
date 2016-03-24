package com.iks.cms.core.gul.container;

/**
 * @author Igor Kaynov
 */
public enum EGulBoxAlign {
  START( "start" ),
  CENTER( "center" ),
  END( "end" ),
  BASELINE( "baseline" ),
  STRETCH( "stretch" );
  private String value;
  private EGulBoxAlign( String value ) {
    this.value = value;
  }
  public String getValue() {
    return value;
  }
  public static EGulBoxAlign getByValue( String value ) {
    for( EGulBoxAlign type : EGulBoxAlign.values() ) {
      if( type.getValue().equals( value ) ) {
        return type;
      }
    }
    return null;
  }
}
