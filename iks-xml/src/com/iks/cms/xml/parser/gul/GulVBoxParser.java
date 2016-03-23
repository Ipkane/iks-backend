package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulVBoxParser< T extends GulVBox > extends GulBoxParser< T > {
  private static GulVBoxParser instance;
  protected GulVBoxParser() {
  }
  public static GulVBoxParser getInstance() {
    if( instance == null ) {
      instance = new GulVBoxParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    gulElement.setOrient( "vertical" );
  }
}
