package com.iks.cms.core.gul.element;

import com.iks.cms.core.gul.*;

/**
 * @author Igor Kaynov
 */
public class GulSpacer extends GulElement {
  @Override
  public String getTemplatePath() {
    return "gul/element";
  }
  @Override
  public String getTag() {
    return GulConstant.SPACER;
  }
}
