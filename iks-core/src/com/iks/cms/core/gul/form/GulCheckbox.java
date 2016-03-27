package com.iks.cms.core.gul.form;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulCheckbox extends AbstractGulField implements IGulInputField {
  private boolean checked;
  @Override
  public String getTag() {
    return GulConstant.CHECKBOX;
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
    if( xmlElement.hasAttribute( GulConstant.ATTR_CHECKED ) ) {
      setChecked( BooleanUtils.toBoolean( xmlElement.getAttribute( GulConstant.ATTR_CHECKED ) ) );
    }
  }
  @Override
  public Object getDefaultValue() {
    return false;
  }
  @Override
  public void applyModel( IDataField dataField ) {
    super.applyModel( dataField );
//    setRequired( dataField.isRequired() );
  }
  public boolean isChecked() {
    return checked;
  }
  public void setChecked( boolean checked ) {
    this.checked = checked;
  }
}
