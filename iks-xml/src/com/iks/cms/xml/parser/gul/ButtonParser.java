package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ButtonParser< T extends GulButton > extends ElementParser< T > {
  private static ButtonParser instance;
  protected ButtonParser() {
  }
  public static ButtonParser getInstance() {
    if( instance == null ) {
      instance = new ButtonParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    gulElement.setLabel( xmlElement.getAttribute( EditConstant.LABEL_ATTR ) );
  }
}
