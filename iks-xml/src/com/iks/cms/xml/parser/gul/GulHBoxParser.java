package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulHBoxParser< T extends GulHBox > extends GulBoxParser< T > {
  private static GulHBoxParser instance;
  protected GulHBoxParser() {
  }
  public static GulHBoxParser getInstance() {
    if( instance == null ) {
      instance = new GulHBoxParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    gulElement.setOrient( "horizontal" );
  }
}
