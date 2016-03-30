package com.iks.cms.web.api.grid;

import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class RequestDeleteManyToManyItem extends AbstractApiRequest {
  private String gridId;
  private String parentItemId;
  private String itemId;
  public String getGridId() {
    return gridId;
  }
  public void setGridId( String gridId ) {
    this.gridId = gridId;
  }
  public String getParentItemId() {
    return parentItemId;
  }
  public void setParentItemId( String parentItemId ) {
    this.parentItemId = parentItemId;
  }
  public String getItemId() {
    return itemId;
  }
  public void setItemId( String itemId ) {
    this.itemId = itemId;
  }
}
