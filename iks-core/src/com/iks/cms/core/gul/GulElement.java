package com.iks.cms.core.gul;

/**
 * @author Igor Kaynov
 */
public abstract class GulElement implements IGulElement {
  protected String id;
  private String tag;
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
  public String getTag() {
    return tag;
  }
  public void setTag( String tag ) {
    this.tag = tag;
  }
  @Override
  public String getTemplateName() {
    return getTag();
  }
}
