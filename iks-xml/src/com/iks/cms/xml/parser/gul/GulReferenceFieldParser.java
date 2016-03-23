package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulReferenceFieldParser< T extends GulReferenceField > extends GulTextboxParser< T > {
  private static GulReferenceFieldParser instance;
  protected GulReferenceFieldParser() {
  }
  public static GulReferenceFieldParser getInstance() {
    if( instance == null ) {
      instance = new GulReferenceFieldParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    gulElement.setDisplayField( xmlElement.getAttribute( GulConstant.DISPLAY_FIELD_ATTR ) );
  }
}
