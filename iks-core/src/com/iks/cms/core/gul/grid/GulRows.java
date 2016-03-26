package com.iks.cms.core.gul.grid;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;

/**
 * @author Igor Kaynov
 */
public class GulRows extends GulContainer {
  @Override
  public String getTag() {
    return GulConstant.ROWS;
  }
  @Override
  public String getTemplatePath() {
    return "gul/grid";
  }
}
