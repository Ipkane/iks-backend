package com.iks.cms.web.api.grid;

import com.iks.cms.core.service.*;
import com.iks.cms.web.api.common.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class RequestGetGridData extends AbstractApiRequest implements IGridRequest {
  private String                gridId;
  private Map< String, Object > filter;
  private String                orderBy;
  private String                parentId;
  private Integer page = 1;
  private Integer limit;
  public Map< String, Object > getFilter() {
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
  @Override
  public String getGridId() {
    return gridId;
  }
  public void setGridId( String gridId ) {
    this.gridId = gridId;
  }
  public String getParentId() {
    return parentId;
  }
  public void setParentId( String parentId ) {
    this.parentId = parentId;
  }
}
