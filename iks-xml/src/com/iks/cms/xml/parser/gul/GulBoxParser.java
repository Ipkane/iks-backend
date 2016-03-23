package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulBoxParser< T extends GulBox > extends GulContainerParser< T > {
  private static GulBoxParser instance;
  protected GulBoxParser() {
  }
  public static GulBoxParser getInstance() {
    if( instance == null ) {
      instance = new GulBoxParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    if (xmlElement.hasAttribute( GulConstant.ORIENT_ATTR )) {
      gulElement.setOrient( xmlElement.getAttribute( GulConstant.ORIENT_ATTR ) );
    }
  }
}
