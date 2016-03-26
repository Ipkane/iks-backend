package com.iks.cms.web.api.grid;

import com.fasterxml.jackson.annotation.*;
import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class RequestGetEditData extends AbstractApiRequest {
  private String appObj;
  private Long   itemId;
  private boolean isNew = false;
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
  public boolean isNew() {
    return isNew;
  }
  public void setIsNew( boolean isNew ) {
    this.isNew = isNew;
  }
}
