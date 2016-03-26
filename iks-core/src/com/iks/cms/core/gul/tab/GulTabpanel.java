package com.iks.cms.core.gul.tab;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;

/**
 * @author Igor Kaynov
 */
public class GulTabpanel extends GulContainer {
  @Override
  public String getTag() {
    return GulConstant.TABPANEL;
  }
  @Override
  public String getTemplatePath() {
    return "gul/tab";
  }
}
