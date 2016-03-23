package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.apache.commons.lang3.*;
import org.hibernate.*;
import org.slf4j.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GridQuery extends CommonDaoQuery {
  private static final Logger logger = LoggerFactory.getLogger( GridQuery.class );
  private IGrid      grid;
  private IDataModel model;
  private String     orderBy;
  private boolean               orderAsc = true;
  private Map< String, Object > filter   = new HashMap<>();
  public GridQuery( IDataModel model, IGrid grid ) {
    this.model = model;
    this.grid = grid;
  }
  public IGrid getGrid() {
    return grid;
  }
  public void setGrid( IGrid grid ) {
    this.grid = grid;
  }
  public List< IDataItem > executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery();
    logger.debug( sqlQuery );
    List rows = selectQuery( sessionFactory, sqlQuery );
    List< IDataItem > resultList = new ArrayList<>();
    for( Object rowData : rows ) {
      DataItem resultItem = new DataItem();
      Object[] data = ( Object[] )rowData;
      int i = 0;
      for( IGridField field : grid.getFields() ) {
        resultItem.addFieldValue( field.getName(), data[i] );
        i++;
      }
      resultList.add( resultItem );
    }
    return resultList;
  }
  private String buildSqlQuery() {
    SelectQuery sb = new SelectQuery();
    Table table = new Table( model.getTableName(), "t" );
    sb.from( table );
    for( IGridField field : grid.getFields() ) {
      IDataField dataField = model.getField( field.getName() );
      sb.addColumn( table.getColumn( dataField.getTableField(), dataField.getName() ) );
    }
    for( Map.Entry< String, Object > entry : filter.entrySet() ) {
      // todo add like filter
      if( entry.getValue() == null || StringUtils.trimToNull( entry.getValue().toString() ) == null ) {
        continue;
      }
      sb.addCriteria( new MatchCriteria( table.getColumn( entry.getKey() ), entry.getValue() ) );
    }
    if( orderBy != null ) {
      sb.orderBy( new ColumnOrder( table.getColumn( orderBy ), orderAsc ? EColumnOrder.ASC : EColumnOrder.DESC ) );
    } else {
      sb.orderBy( new ColumnOrder( table.getColumn( model.getPrimaryFieldName(), null ), EColumnOrder.ASC ) );
    }
    return sb.toString();
  }
  public Map< String, Object > getFilter() {
    return filter;
  }
  public GridQuery setFilter( Map< String, Object > filter ) {
    this.filter = filter;
    return this;
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
}
