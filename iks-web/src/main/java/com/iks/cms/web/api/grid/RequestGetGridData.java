package com.iks.cms.web.api.grid;

import com.iks.cms.web.api.common.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class RequestGetGridData extends AbstractApiRequest {
  private String                appObj;
  private Map< String, Object > filter;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  public Map< String, Object > getFilter() {
    return filter;
  }
  public void setFilter( Map< String, Object > filter ) {
    this.filter = filter;
  }
}
