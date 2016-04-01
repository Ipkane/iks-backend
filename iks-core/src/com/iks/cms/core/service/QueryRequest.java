package com.iks.cms.core.service;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class QueryRequest implements IQueryRequest {
  private Map< String, Object > filter;
  private String                orderBy;
  private String                parentId;
  private Integer page = 1;
  private Integer limit;
  public Map< String, Object > getFilter() {
    if (filter == null) {
      filter = new HashMap<>(  );
    }
    return filter;
  }
  public void setFilter( Map< String, Object > filter ) {
    this.filter = filter;
  }
  public String getOrderBy() {
    return orderBy;
  }
  public void setOrderBy( String orderBy ) {
    this.orderBy = orderBy;
  }
  public Integer getPage() {
    return page;
  }
  public void setPage( int page ) {
    this.page = page;
  }
  public Integer getLimit() {
    return limit;
  }
  public void setLimit( Integer limit ) {
    this.limit = limit;
  }
  public String getParentId() {
    return parentId;
  }
  public void setParentId( String parentId ) {
    this.parentId = parentId;
  }
}
