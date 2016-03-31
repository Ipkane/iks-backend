package com.iks.cms.core.grid;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class PageableResult {
  private List< IDataItem > items;
  private Integer           limit;
  private Integer           offset;
  private Long           total;
  public Integer getOffset() {
    return offset;
  }
  public void setOffset( Integer startItem ) {
    this.offset = startItem;
  }
  public Integer getLimit() {
    return limit;
  }
  public void setLimit( Integer limit ) {
    this.limit = limit;
  }
  public List< IDataItem > getItems() {
    return items;
  }
  public void setItems( List< IDataItem > items ) {
    this.items = items;
  }
  public Long getTotal() {
    return total;
  }
  public void setTotal( Long totalItems ) {
    this.total = totalItems;
  }
}
