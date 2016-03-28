package com.iks.cms.core.query;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.join.*;
import com.iks.cms.core.sql.projection.*;
import com.iks.cms.core.sql.query.*;
import com.iks.cms.core.utils.*;

import org.apache.commons.lang3.*;
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
      resultList.add( parseResult( rowData ) );
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
      resultList.add( parseResult( rowData ) );
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
    if (filters == null || filters.size() == 0) {
      query.getLeftJoins().clear();
    }
    query.addProjection( Projections.rowCount( null ) );
    query.setLimit( 0 );
    query.setOffset( 0 );
    String sqlQuery = query.toString();
    logger.debug( sqlQuery );
    BigInteger count= (BigInteger )selectSingleQuery( sessionFactory, sqlQuery );
    return count.longValue();
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
    for( String field : getFields() ) {
      String[] parts = ModelUtils.splitField( field );
      String value = data[i] == null ? null : data[i].toString();
      if( parts.length == 1 ) {
        IDataField dataField = model.getField( field );
        if( dataField instanceof ManyToOne ) {
          ManyToOne manyToOne = ( ManyToOne )dataField;
          DataItem joinedItem = new DataItem();
          joinedItem.addFieldValue( manyToOne.getReferenceField(), value );
          resultItem.addFieldValue( manyToOne.getName(), joinedItem );
        } else {
          resultItem.addFieldValue( dataField.getName(), dataField.parseValue( value ) );
        }
      } else if( parts.length == 2 ) {
        ManyToOne manyToOne = ( ManyToOne )model.getField( parts[0] );
        DataItem joinedItem = ( DataItem )resultItem.getFieldValue( manyToOne.getName() );
        if( joinedItem == null ) {
          joinedItem = new DataItem();
          resultItem.addFieldValue( manyToOne.getName(), joinedItem );
        }
        joinedItem.addFieldValue( parts[1], value );
      } else {
        throw new RuntimeException( "Method doesn't support yet form complex fields" );
      }
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
      if( parts.length == 1 ) {
        IDataField dataField = model.getField( parts[0] );
        sb.addColumn( table.getColumn( dataField.getTableField(), dataField.getName() ) );
      } else if( parts.length == 2 ) {
        // join tables
        ManyToOne dataField = ( ManyToOne )model.getField( parts[0] );
        IDataModel joinedModel = App.getModel( dataField.getAppObj() );
        IDataField joinedField = joinedModel.getField( parts[1] );
        Table joinedTable = new Table( joinedModel.getTableName(), joinedModel.getAppObj() );
        sb.leftJoin( new Join( joinedTable, table.getColumn( dataField.getTableField(), dataField.getName() ), joinedTable.getColumn( dataField.getReferenceField() ) ) );
        sb.addColumn( joinedTable.getColumn( joinedField.getTableField(), joinedField.getName() ) );
      }
    }
    for( Map.Entry< String, Object > entry : filters.entrySet() ) {
      // todo add like filter
      IDataField dataField = model.getField( entry.getKey() );
      if( dataField instanceof SimpleDataField ) {
        // simple field query, just key and value
        if( entry.getValue() == null || StringUtils.trimToNull( entry.getValue().toString() ) == null ) {
          continue;
        }
        sb.addCriteria( new MatchCriteria( table.getColumn( entry.getKey() ), entry.getValue() ) );
      } else if( dataField instanceof ManyToOne ) {
        // query to many to one field. Herer value is a map with key/value pair
        ManyToOne manyToOne = ( ManyToOne )dataField;
        IDataModel joinedModel = App.getModel( manyToOne.getAppObj() );
        Table joinedTable = new Table( joinedModel.getTableName(), joinedModel.getAppObj() );
        sb.leftJoin( new Join( joinedTable, table.getColumn( dataField.getTableField(), dataField.getName() ), joinedTable.getColumn( manyToOne.getReferenceField() ) ) );
        for( Map.Entry< String, Object > joinedItemEntry : ( ( Map< String, Object > )entry.getValue() ).entrySet() ) {
          IDataField joinedField = joinedModel.getField( joinedItemEntry.getKey() );
          if( joinedItemEntry.getValue() == null || StringUtils.trimToNull( joinedItemEntry.getValue().toString() ) == null ) {
            continue;
          }
          sb.addCriteria( new MatchCriteria( joinedTable.getColumn( joinedField.getTableField() ), joinedItemEntry.getValue() ) );
        }
      } else {
        throw new RuntimeException( "Method not implemented for this type of field" );
      }
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
