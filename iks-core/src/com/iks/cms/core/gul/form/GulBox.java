package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulBox extends GulContainer {
  private String orient = "horizontal";
  @Override
  public String getTemplatePath() {
    return "gul/box";
  }
  public String getOrient() {
    return orient;
  }
  public void setOrient( String orient ) {
    this.orient = orient;
  }
  @Override
  public void parse( IDataModel model, Element xmlElement ) throws Exception {
    super.parse( model, xmlElement );
    if (xmlElement.hasAttribute( GulConstant.ORIENT_ATTR )) {
      setOrient( xmlElement.getAttribute( GulConstant.ORIENT_ATTR ) );
    }
  }
}
