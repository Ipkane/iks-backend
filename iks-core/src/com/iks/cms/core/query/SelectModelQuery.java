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
import java.util.stream.*;

/**
 * @author Igor Kaynov
 */
public class SelectModelQuery< T extends SelectModelQuery > extends CommonModelQuery< T > {
  private static final Logger logger = LoggerFactory.getLogger( SelectModelQuery.class );
  private String orderBy;
  private boolean               orderAsc = true;
  private Map< String, Object > filters  = new HashMap<>();
  public SelectModelQuery( IDataModel model ) {
    super( model );
  }
  public List< IDataItem > executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery().toString();
    logger.debug( sqlQuery );
    List rows = selectQuery( sessionFactory, sqlQuery );
    List< IDataItem > resultList = new ArrayList<>();
    for( Object rowData : rows ) {
      resultList.add( parseResult( rowData ) );
    }
    return resultList;
  }
  public IDataItem executeSingleQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery().toString();
    logger.debug( sqlQuery );
    Object row = selectSingleQuery( sessionFactory, sqlQuery );
    return parseResult( row );
  }
  protected IDataItem parseResult( Object rawData ) {
    DataItem resultItem = new DataItem();
    Object[] data = ( Object[] )rawData;
    int i = 0;
    for( IDataField field : getFields() ) {
      String value = data[i] == null ? null : data[i].toString();
      resultItem.addFieldValue( field.getName(), field.parseValue(value) );
      i++;
    }
    return resultItem;
  }
  protected SelectQuery buildSqlQuery() {
    SelectQuery sb = new SelectQuery();
    Table table = new Table( model.getTableName(), "t" );
    sb.from( table );
    for( IDataField field : getFields() ) {
      sb.addColumn( new Column( table, field.getTableField(), field.getName() ) );
    }
    for( Map.Entry< String, Object > entry : filters.entrySet() ) {
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
}
