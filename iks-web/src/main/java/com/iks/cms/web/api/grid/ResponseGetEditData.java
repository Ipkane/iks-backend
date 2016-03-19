package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class ResponseGetEditData extends AbstractApiResponse {
  private IDataRow item;
  public IDataRow getItem() {
    return item;
  }
  public void setItem( IDataRow item ) {
    this.item = item;
  }
}
