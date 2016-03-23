package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ReferenceFieldParser< T extends GulReferenceField > extends InputFieldParser< T > {
  private static ReferenceFieldParser instance;
  protected ReferenceFieldParser() {
  }
  public static ReferenceFieldParser getInstance() {
    if( instance == null ) {
      instance = new ReferenceFieldParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    gulElement.setDisplayField( xmlElement.getAttribute( EditConstant.DISPLAY_FIELD_ATTR ) );
  }
}
