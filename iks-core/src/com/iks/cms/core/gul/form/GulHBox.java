package com.iks.cms.core.gul.form;

import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulHBox extends GulBox {
  public GulHBox() {
    super();
    this.setOrient( "horizontal" );
  }
  @Override
  public void parse( IDataModel model, Element xmlElement ) throws Exception {
    super.parse( model, xmlElement );
    setOrient( "horizontal" );
  }
}
