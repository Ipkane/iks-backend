package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ReferenceSelectFieldParser< T extends GulReferenceSelectField > extends ReferenceFieldParser< T > {
  private static ReferenceSelectFieldParser instance;
  protected ReferenceSelectFieldParser() {
  }
  public static ReferenceSelectFieldParser getInstance() {
    if( instance == null ) {
      instance = new ReferenceSelectFieldParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
  }
}
