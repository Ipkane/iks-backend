package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GulButton extends GulElement {
  private String label;
  @Override
  public String getTemplatePath() {
    return "gul/form";
  }
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
}
