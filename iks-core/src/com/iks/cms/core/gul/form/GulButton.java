package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulButton extends GulElement {
  private String label;
  @Override
  public String getTemplatePath() {
    return "gul/form";
  }
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
  @Override
  public void parse( IDataModel model, Element xmlElement ) throws Exception {
    super.parse( model, xmlElement );
    setLabel( xmlElement.getAttribute( GulConstant.LABEL_ATTR ) );
  }
}
