package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulElementParser< T extends GulElement > extends AbstractGulParser< T > {
  private static GulElementParser instance;
  protected GulElementParser() {
  }
  public static GulElementParser getInstance() {
    if( instance == null ) {
      instance = new GulElementParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    gulElement.setTag( xmlElement.getTagName() );
    gulElement.setId( xmlElement.getAttribute( GulConstant.LABEL_ATTR ) );
  }
}
