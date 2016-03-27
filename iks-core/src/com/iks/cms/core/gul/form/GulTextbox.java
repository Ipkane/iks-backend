package com.iks.cms.core.gul.form;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulTextbox extends AbstractGulField implements IGulInputField {
  private boolean required     = false;
  private Object  defaultValue = null;
  @Override
  public String getTag() {
    return GulConstant.TEXTBOX;
  }
  public boolean isRequired() {
    return required;
  }
  public void setRequired( boolean required ) {
    this.required = required;
  }
  public void setDefaultValue( Object defaultValue ) {
    this.defaultValue = defaultValue;
  }
  @Override
  public Object getDefaultValue() {
    return defaultValue;
  }
  @Override
  public String getTemplatePath() {
    return "gul/form";
  }
  @Override
  public String getTemplateName() {
    return getTag();
  }
  @Override
  public void parse( Element xmlElement ) throws Exception {
    super.parse(xmlElement );
    if( xmlElement.hasAttribute( GulConstant.ATTR_REQUIRED ) ) {
      setRequired( BooleanUtils.toBoolean( xmlElement.getAttribute( GulConstant.ATTR_REQUIRED ) ) );
    }
  }
  @Override
  public void applyModel( IDataField dataField ) {
    super.applyModel( dataField );
//    setRequired( dataField.isRequired() );
  }
}
