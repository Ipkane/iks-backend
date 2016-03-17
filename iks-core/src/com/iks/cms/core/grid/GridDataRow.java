package com.iks.cms.core.grid;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GridDataRow implements IGridDataRow {
  private Map< String, Object > data = new LinkedHashMap<>();
  @JsonValue
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
