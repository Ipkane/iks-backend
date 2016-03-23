package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulButtonParser< T extends GulButton > extends GulElementParser< T > {
  private static GulButtonParser instance;
  protected GulButtonParser() {
  }
  public static GulButtonParser getInstance() {
    if( instance == null ) {
      instance = new GulButtonParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    gulElement.setLabel( xmlElement.getAttribute( GulConstant.LABEL_ATTR ) );
  }
}
