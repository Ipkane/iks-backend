package com.iks.cms.core.gul.grid;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;

/**
 * @author Igor Kaynov
 */
public class GulColumns extends GulContainer {
  @Override
  public String getTag() {
    return GulConstant.COLUMNS;
  }
  @Override
  public String getTemplatePath() {
    return "gul/grid";
  }
}
