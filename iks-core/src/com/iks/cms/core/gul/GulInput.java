package com.iks.cms.core.gul;

/**
 * @author Igor Kaynov
 */
public class GulInput implements IGulElement {
  private String name;
  private String label;
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
}
