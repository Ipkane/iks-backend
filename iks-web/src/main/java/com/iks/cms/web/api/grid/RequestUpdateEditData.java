package com.iks.cms.web.api.grid;

import com.fasterxml.jackson.annotation.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUpdateEditData extends AbstractApiRequest {
  private String   appObj;
  private DataItem item;
  private boolean isNew = false;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  public DataItem getItem() {
    return item;
  }
  public void setItem( DataItem item ) {
    this.item = item;
  }
  public boolean isNew() {
    return isNew;
  }
  public void setIsNew( boolean isNew ) {
    this.isNew = isNew;
  }
}
