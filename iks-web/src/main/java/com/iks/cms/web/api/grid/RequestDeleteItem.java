package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class RequestDeleteItem extends AbstractApiRequest {
  private String gridId;
  private Long   itemId;
  public Long getItemId() {
    return itemId;
  }
  public void setItemId( Long itemId ) {
    this.itemId = itemId;
  }
  public String getGridId() {
    return gridId;
  }
  public void setGridId( String gridId ) {
    this.gridId = gridId;
  }
}
