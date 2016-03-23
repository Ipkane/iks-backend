package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ElementParser< T extends GulElement > implements IGulParser< T > {
  private static ElementParser instance;
  protected ElementParser() {
  }
  public static ElementParser getInstance() {
    if( instance == null ) {
      instance = new ElementParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    gulElement.setId( xmlElement.getAttribute( EditConstant.LABEL_ATTR ) );
  }
}
