package com.iks.cms.core.gul.tab;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulTab extends GulContainer {
  private String label;
  @Override
  public String getTag() {
    return GulConstant.TAB;
  }

  @Override
  public String getTemplatePath() {
    return "gul/tab";
  }
  public String getLabel() {
    return label;
  }
  @Override
  public void parse( Element xmlElement ) throws Exception {
    super.parse( xmlElement );
    if (xmlElement.hasAttribute( GulConstant.ATTR_LABEL )) {
      setLabel( xmlElement.getAttribute(GulConstant.ATTR_LABEL) );
    }
  }
  public void setLabel( String label ) {
    this.label = label;
  }
}
