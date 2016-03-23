package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class ResponseGetEditData extends AbstractApiResponse {
  private IDataItem item;
  public IDataItem getItem() {
    return item;
  }
  public void setItem( IDataItem item ) {
    this.item = item;
  }
}
