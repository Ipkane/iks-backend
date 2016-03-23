package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulReferenceSelectFieldParser< T extends GulReferenceSelect > extends GulReferenceFieldParser< T > {
  private static GulReferenceSelectFieldParser instance;
  protected GulReferenceSelectFieldParser() {
  }
  public static GulReferenceSelectFieldParser getInstance() {
    if( instance == null ) {
      instance = new GulReferenceSelectFieldParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
  }
}
