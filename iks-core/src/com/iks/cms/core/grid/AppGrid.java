package com.iks.cms.core.grid;

import com.iks.cms.core.constant.*;

/**
 * @author Igor Kaynov
 */
public class AppGrid extends BaseGrid implements IBaseGrid {
  @Override
  public String getTag() {
    return ListConstant.APP_TABLE;
  }
  @Override
  public String getTemplatePath() {
    return "app/appTable";
  }
}
