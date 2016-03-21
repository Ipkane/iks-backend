package com.iks.cms.core.gul;

/**
 * @author Igor Kaynov
 */
public class SelectOption {
  private String value;
  private String label;
  public SelectOption() {

  }
  public SelectOption(String value, String label) {
    this.value = value;
    this.label = label;
  }
  public String getValue() {
    return value;
  }
  public void setValue( String value ) {
    this.value = value;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
}
