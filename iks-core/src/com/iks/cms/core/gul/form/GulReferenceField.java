package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulReferenceField extends GulTextbox {
  private String displayField;
  public String getDisplayField() {
    return displayField;
  }
  public void setDisplayField( String displayField ) {
    this.displayField = displayField;
  }
  @Override
  public String getTag() {
    return GulConstant.REFERENCE_FIELD;
  }
  @Override
  public void parse( Element xmlElement ) throws Exception {
    super.parse( xmlElement );
    setDisplayField( xmlElement.getAttribute( GulConstant.ATTR_DISPLAY_FIELD ) );
  }
}
