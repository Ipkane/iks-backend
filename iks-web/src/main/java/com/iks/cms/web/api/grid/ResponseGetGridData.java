package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ResponseGetGridData extends AbstractApiResponse {
  private List< IDataItem > items;
  public List< IDataItem > getItems() {
    return items;
  }
  public void setItems( List< IDataItem > items ) {
    this.items = items;
  }
}
