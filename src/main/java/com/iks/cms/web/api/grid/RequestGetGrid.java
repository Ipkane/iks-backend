package com.iks.cms.web.api.grid;

import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class RequestGetGrid extends AbstractApiRequest {
  private String gridName;
  public String getGridName() {
    return gridName;
  }
  public void setGridName( String gridName ) {
    this.gridName = gridName;
  }
}
