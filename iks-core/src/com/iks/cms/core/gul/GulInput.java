package com.iks.cms.core.gul;

/**
 * @author Igor Kaynov
 */
public class GulInput implements IGulInput {
  private String name;
  private String label;
  private boolean required = false;
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
}
