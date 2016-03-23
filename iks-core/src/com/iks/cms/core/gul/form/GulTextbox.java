package com.iks.cms.core.gul.form;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulTextbox extends GulElement implements IGulInputField {
  private String name;
  private String label;
  private boolean required     = false;
  private boolean readonly     = false;
  private Object  defaultValue = null;
  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
  public boolean isRequired() {
    return required;
  }
  public void setRequired( boolean required ) {
    this.required = required;
  }
  public boolean isReadonly() {
    return readonly;
  }
  public void setReadonly( boolean readonly ) {
    this.readonly = readonly;
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
  public void parse( IDataModel model, Element xmlElement ) throws Exception {
    super.parse( model, xmlElement );
    setName( xmlElement.getAttribute( GulConstant.NAME_ATTR ) );
    DataField dataField = ( DataField )model.getField( getName() );
    if( xmlElement.hasAttribute( GulConstant.LABEL_ATTR ) ) {
      setLabel( xmlElement.getAttribute( GulConstant.LABEL_ATTR ) );
    } else {
      setLabel( dataField.getLabel() );
    }
    if( xmlElement.hasAttribute( GulConstant.REQUIRED_ATTR ) ) {
      setRequired( BooleanUtils.toBoolean( xmlElement.getAttribute( GulConstant.REQUIRED_ATTR ) ) );
    } else {
      setRequired( dataField.isRequired() );
    }
    if( xmlElement.hasAttribute( GulConstant.READONLY_ATTR ) ) {
      setReadonly( BooleanUtils.toBoolean( xmlElement.getAttribute( GulConstant.READONLY_ATTR ) ) );
    }
  }
}
