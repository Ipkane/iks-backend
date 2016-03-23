package com.iks.cms.core.gul;

/**
 * @author Igor Kaynov
 */
public abstract class GulElement implements IGulElement {
  @Override
  public abstract String getTemplatePath();
  @Override
  public String getTemplateName() {
    return "template";
  }
}
