package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;

/**
 * @author Igor Kaynov
 */
public class GulBox extends GulContainer {
  private String orient = "horizontal";
  @Override
  public String getTemplatePath() {
    return "gul/box";
  }
  public String getOrient() {
    return orient;
  }
  public void setOrient( String orient ) {
    this.orient = orient;
  }
}
