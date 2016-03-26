package com.iks.cms.core.gul.tab;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;

/**
 * @author Igor Kaynov
 */
public class GulTabpanels extends GulContainer {
  @Override
  public String getTag() {
    return GulConstant.TABPANELS;
  }
  @Override
  public String getTemplatePath() {
    return "gul/tab";
  }
}
