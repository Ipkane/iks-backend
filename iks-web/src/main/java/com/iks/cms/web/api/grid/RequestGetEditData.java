package com.iks.cms.web.api.grid;

import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class RequestGetEditData extends AbstractApiRequest {
  private String appObj;
  private Long   itemId;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  public Long getItemId() {
    return itemId;
  }
  public void setItemId( Long itemId ) {
    this.itemId = itemId;
  }
}
