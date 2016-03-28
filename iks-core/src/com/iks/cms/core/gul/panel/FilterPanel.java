package com.iks.cms.core.gul.panel;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.model.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class FilterPanel extends GulFieldContainer implements IFilterPanel {
  private String appObj;
  public FilterPanel() {
    super();
  }
  @Override
  public String getTag() {
    return GulConstant.FILTER_PANEL;
  }
  @Override
  public String getTemplatePath() {
    return "app/filterPanel";
  }
  @Override
  public String getTemplateName() {
    return "filterPanel";
  }
  public Map< String, List< SelectOption > > getOptionsMap() {
    return App.getInstance().getOptionsMap( appObj, getFields() );
  }
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
    setModel( App.getModel( appObj ) );
  }
}
