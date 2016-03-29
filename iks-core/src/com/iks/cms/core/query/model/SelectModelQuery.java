package com.iks.cms.core.query.model;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.projection.*;
import com.iks.cms.core.sql.query.*;
import com.iks.cms.core.utils.*;
import com.sun.istack.internal.*;

import org.hibernate.*;
import org.slf4j.*;

import java.math.*;
import java.util.*;

/**
 * @author Igor Kaynov
 */
public class SelectModelQuery< T extends SelectModelQuery > extends CommonModelQuery< T > {
  private static final Logger logger = LoggerFactory.getLogger( SelectModelQuery.class );
  private String orderBy;
  private boolean               orderAsc = true;
  private Map< String, Object > filters  = new HashMap<>();
  private Integer offset;
  private Integer limit;
  public SelectModelQuery( IDataModel model ) {
    super( model );
  }
  public List< IDataItem > executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery().toString();
    logger.debug( sqlQuery );
    List rows = selectQuery( sessionFactory, sqlQuery );
    List< IDataItem > resultList = new ArrayList<>();
    for( Object rowData : rows ) {
      IDataItem item = parseResult( rowData );
      if (item != null) {
        resultList.add( item );
      }
    }
    return resultList;
  }
  public PageableResult getPageableResult( SessionFactory sessionFactory ) {
    SelectQuery query = buildSqlQuery();
    String sqlQuery = query.toString();
    logger.debug( sqlQuery );
    List rows = selectQuery( sessionFactory, sqlQuery );
    List< IDataItem > resultList = new ArrayList<>();
    for( Object rowData : rows ) {
      IDataItem item = parseResult( rowData );
      if (item != null) {
        resultList.add(item);
      }
    }
    PageableResult result = new PageableResult();
    result.setItems( resultList );
    result.setLimit( limit );
    result.setOffset( offset );
    result.setTotalItems( getCount( sessionFactory ) );
    return result;
  }
  public Long getCount( SessionFactory sessionFactory ) {
    SelectQuery query = buildSqlQuery();
    query.getColumns().clear();
    query.getOrderBys().clear();
    //    if (filters == null || filters.size() == 0) {
    //      query.getLeftJoins().clear();
    //    }
    query.addProjection( Projections.rowCount() );
    query.setLimit( 0 );
    query.setOffset( 0 );
    String sqlQuery = query.toString();
    logger.debug( sqlQuery );
    BigInteger count = ( BigInteger )selectSingleQuery( sessionFactory, sqlQuery );
    return count.longValue();
  }
  public IDataItem executeSingleQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery().toString();
    logger.debug( sqlQuery );
    Object row = selectSingleQuery( sessionFactory, sqlQuery );
    return parseResult( row );
  }
  protected @Nullable IDataItem parseResult( Object rawData ) {
    DataItem resultItem = new DataItem();
    Object[] data = ( Object[] )rawData;
    if (ModelUtils.isEmptyArray(data)) {
      return null;
    }
    int i = 0;
    for( String field : getFields() ) {
      String[] parts = ModelUtils.splitField( field );
      String value = data[i] == null ? null : data[i].toString();
      IDataField dataField = model.getField( parts[0] );
      dataField.fillSelectQueryResult( resultItem, value, field );
      i++;
    }
    return resultItem;
  }
  protected SelectQuery buildSqlQuery() {
    SelectQuery sb = new SelectQuery();
    Table table = new Table( model.getTableName(), model.getAppObj() );
    sb.from( table );
    for( String field : getFields() ) {
      String[] parts = ModelUtils.splitField( field );
      IDataField dataField = model.getField( parts[0] );
      dataField.extendSelectQueryFields( sb, field );
    }
    for( Map.Entry< String, Object > entry : filters.entrySet() ) {
      // todo add like filter
      IDataField dataField = model.getField( entry.getKey() );
      dataField.extendSelectQueryFilter( sb, entry.getValue() );
    }
    if( orderBy != null ) {
      sb.orderBy( new ColumnOrder( table.getColumn( orderBy ), orderAsc ? EColumnOrder.ASC : EColumnOrder.DESC ) );
    } else {
      sb.orderBy( new ColumnOrder( table.getColumn( model.getPrimaryFieldName(), null ), EColumnOrder.ASC ) );
    }
    sb.setLimit( limit );
    sb.setOffset( offset );
    return sb;
  }
  public String getOrderBy() {
    return orderBy;
  }
  public void setOrderBy( String orderBy ) {
    this.orderBy = orderBy;
  }
  public boolean isOrderAsc() {
    return orderAsc;
  }
  public void setOrderAsc( boolean orderAsc ) {
    this.orderAsc = orderAsc;
  }
  public Map< String, Object > getFilters() {
    return filters;
  }
  public T setFilters( Map< String, Object > filters ) {
    this.filters = filters;
    return ( T )this;
  }
  public T addFilter( String fieldName, Object value ) {
    this.filters.put( fieldName, value );
    return ( T )this;
  }
  public Integer getLimit() {
    return limit;
  }
  public void setLimit( Integer limit ) {
    this.limit = limit;
  }
  public Integer getOffset() {
    return offset;
  }
  public void setOffset( Integer offset ) {
    this.offset = offset;
  }
}
