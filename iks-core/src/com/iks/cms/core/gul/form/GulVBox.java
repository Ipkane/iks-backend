package com.iks.cms.core.gul.form;

import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulVBox extends GulBox {
  public GulVBox() {
    super();
    this.setOrient( "vertical" );
  }
  @Override
  public void parse( IDataModel model, Element xmlElement ) throws Exception {
    super.parse( model, xmlElement );
    setOrient( "vertical" );
  }
}
