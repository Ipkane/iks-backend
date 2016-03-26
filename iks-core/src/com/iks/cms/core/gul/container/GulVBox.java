package com.iks.cms.core.gul.container;

import com.iks.cms.core.gul.*;
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
  public String getTag() {
    return GulConstant.VBOX;
  }
  @Override
  public void parse( Element xmlElement ) throws Exception {
    super.parse( xmlElement );
    setOrient( "vertical" );
  }
}
