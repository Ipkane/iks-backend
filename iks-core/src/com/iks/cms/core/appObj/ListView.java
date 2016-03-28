package com.iks.cms.core.appObj;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;

/**
 * @author Igor Kaynov
 */
public class ListView extends GulContainer implements IListView {
  @Override
  public String getTemplatePath() {
    return "view/listView";
  }
  @Override
  public String getTag() {
    return GulConstant.LIST_VIEW;
  }
}
