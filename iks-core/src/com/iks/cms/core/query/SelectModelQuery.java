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
public class SelectModelQuery extends CommonDaoQuery {
  private static final Logger logger = LoggerFactory.getLogger( SelectModelQuery.class );
  private IDataModel model;
  private String     orderBy;
  private boolean               orderAsc = true;
  private List< IDataField >    fields   = new ArrayList<>();
  private Map< String, Object > filter   = new HashMap<>();
  public SelectModelQuery( IDataModel model ) {
    this.model = model;
  }
  public List< IDataItem > executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery();
    logger.debug( sqlQuery );
    List rows = selectQuery( sessionFactory, sqlQuery );
    List< IDataItem > resultList = new ArrayList<>();
    for( Object rowData : rows ) {
      resultList.add( parseResult( rowData ) );
    }
    return resultList;
  }
  public IDataItem  executeSingleQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery();
    logger.debug( sqlQuery );
    Object row = selectSingleQuery( sessionFactory, sqlQuery );
    return parseResult( row );
  }
  protected IDataItem parseResult(Object rawData) {
    DataItem resultItem = new DataItem();
    Object[] data = ( Object[] )rawData;
    int i = 0;
    for( IDataField field : getFields() ) {
      String value = data[i] == null ? null : data[i].toString();
      resultItem.addFieldValue( field.getName(), value );
      i++;
    }
    return resultItem;
  }
  protected String buildSqlQuery() {
    SelectQuery sb = new SelectQuery();
    Table table = new Table( model.getTableName(), "t" );
    sb.from( table );
    for( IDataField field : getFields() ) {
      sb.addColumn( new Column( table, field.getTableField(), field.getName() ) );
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
  public List< IDataField > getFields() {
    return fields.size() == 0 ? model.getFields() : fields;
  }
  public SelectModelQuery setFields( List< String > fieldsList ) {
    this.fields.clear();
    this.fields.addAll( fieldsList.stream().map( model::getField ).collect( Collectors.toList() ) );
    return this;
  }
  public SelectModelQuery addField( String field ) {
    this.fields.add( model.getField( field ) );
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
  public Map< String, Object > getFilter() {
    return filter;
  }
  public SelectModelQuery setFilter( Map< String, Object > filter ) {
    this.filter = filter;
    return this;
  }
  public SelectModelQuery addFilter(String fieldName, Object value) {
    this.filter.put( fieldName, value );
    return this;
  }
}
