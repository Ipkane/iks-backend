package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ResponseGetGridData extends AbstractApiResponse {
  private List< IDataRow > items;
  public List< IDataRow > getItems() {
    return items;
  }
  public void setItems( List< IDataRow > items ) {
    this.items = items;
  }
}
