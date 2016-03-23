package com.iks.cms.core.gul;

/**
 * @author Igor Kaynov
 */
public abstract class GulElement implements IGulElement {
  protected String id;
  @Override
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  @Override
  public abstract String getTemplatePath();
  @Override
  public String getTemplateName() {
    return "template";
  }
}
