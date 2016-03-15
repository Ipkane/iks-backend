package com.iks.cms.core.grid;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GridDataRow implements IGridDataRow {
  private Map< String, Object > data = new HashMap<>();
  public Map< String, Object > getData() {
    return data;
  }
  public void setData( Map< String, Object > data ) {
    this.data = data;
  }
  public void addData( String fieldName, Object value ) {
    data.put( fieldName, value );
  }
  ;
}
