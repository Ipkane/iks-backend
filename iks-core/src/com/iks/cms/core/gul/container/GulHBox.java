package com.iks.cms.core.gul.container;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

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
  public String getTag() {
    return GulConstant.HBOX;
  }
  @Override
  public void parse(IParseContext context,  Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
    setOrient( "horizontal" );
  }
}
