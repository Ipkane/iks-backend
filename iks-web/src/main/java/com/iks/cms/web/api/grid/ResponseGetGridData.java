package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ResponseGetGridData extends AbstractApiResponse {
  private List< IGridDataRow > data;
  public List< IGridDataRow > getData() {
    return data;
  }
  public void setData( List< IGridDataRow > data ) {
    this.data = data;
  }
}
