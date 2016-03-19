package com.iks.cms.web.api.grid;

import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class RequestGetGrid extends AbstractApiRequest {
  private String appObj;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
}
