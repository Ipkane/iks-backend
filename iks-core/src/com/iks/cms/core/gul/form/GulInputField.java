package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;

/**
 * @author Igor Kaynov
 */
public class GulInputField implements IGulInputField {
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
  public String getType() {
    return GulConstant.INPUT_TYPE;
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
    return getType();
  }
}
