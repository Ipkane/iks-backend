package com.iks.cms.core.gul.container;

/**
 * @author Igor Kaynov
 */
public enum EGulBoxPack {
  START( "start" ),
  CENTER( "center" ),
  END( "end" );
  private String value;
  private EGulBoxPack( String value ) {
    this.value = value;
  }
  public String getValue() {
    return value;
  }
  public static EGulBoxPack getByValue( String value ) {
    for( EGulBoxPack type : EGulBoxPack.values() ) {
      if( type.getValue().equals( value ) ) {
        return type;
      }
    }
    return null;
  }
}
