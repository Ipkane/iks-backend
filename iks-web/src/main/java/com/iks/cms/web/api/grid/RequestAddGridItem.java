package com.iks.cms.web.api.grid;

import com.fasterxml.jackson.annotation.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestAddGridItem extends AbstractApiRequest {
  private String gridId;
  private String parentItemId;
  private String itemId;
  public String getGridId() {
    return gridId;
  }
  public void setGridId( String gridId ) {
    this.gridId = gridId;
  }
  public String getItemId() {
    return itemId;
  }
  public void setItemId( String itemId ) {
    this.itemId = itemId;
  }
  public String getParentItemId() {
    return parentItemId;
  }
  public void setParentItemId( String parentItemId ) {
    this.parentItemId = parentItemId;
  }
}
