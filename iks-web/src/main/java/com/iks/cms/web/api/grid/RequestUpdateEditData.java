package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class RequestUpdateEditData extends AbstractApiRequest {
  private String  appObj;
  private DataRow item;
  private boolean isNew = false;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  public DataRow getItem() {
    return item;
  }
  public void setItem( DataRow item ) {
    this.item = item;
  }
  public boolean isNew() {
    return isNew;
  }
  public void setIsNew( boolean isNew ) {
    this.isNew = isNew;
  }
}
