package com.iks.cms.core.data;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.constant.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.join.*;
import com.iks.cms.core.sql.query.*;
import com.iks.cms.core.utils.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ManyToOne extends AbstractDataField {
  private String appObj;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  /**
   * Primary field of referenced model
   */
  public String getReferenceField() {
    return App.getModel( appObj ).getPrimaryFieldName();
  }
  @Override
  public void parse( Element xmlElement ) {
    super.parse( xmlElement );
    setAppObj( xmlElement.getAttribute( ModelConstant.APP_OBJ_ATTR ) );
  }
  @Override
  public void extendSelectQueryFields( SelectQuery query, String fullField ) {
    Table table = query.getMainTable();
    String[] parts = ModelUtils.splitField( fullField );
    if( parts.length == 1 ) {
      query.addColumn( table.getColumn( getTableField(), getFieldName() ) );
    } else {
      IDataModel joinedModel = App.getModel( getAppObj() );
      IDataField joinedField = joinedModel.getField( parts[1] );
      Table joinedTable = new Table( joinedModel.getTableName(), joinedModel.getAppObj() );
      query.leftJoin( new Join( joinedTable, table.getColumn( getTableField(), getFieldName() ), joinedTable.getColumn( joinedModel.getPrimaryFieldName() ) ) );
      query.addColumn( joinedTable.getColumn( joinedField.getTableField(), joinedField.getFieldName() ) );
    }
  }
  @Override
  public void extendSelectQueryFilter( SelectQuery query, String fullField, Object value ) {
    Table table = query.getMainTable();
    IDataModel joinedModel = App.getModel( getAppObj() );
    Table joinedTable = new Table( joinedModel.getTableName(), joinedModel.getAppObj() );
    String[] parts = ModelUtils.splitField( fullField );
    if( parts.length == 1 ) {
      query.addCriteria( new MatchCriteria( joinedTable.getColumn( getTableField() ), value ) );
    } else {
      query.leftJoin( new Join( joinedTable, table.getColumn( getTableField(), getFieldName() ), joinedTable.getColumn( joinedModel.getPrimaryFieldName() ) ) );
      for( Map.Entry< String, Object > joinedItemEntry : ( ( Map< String, Object > )value ).entrySet() ) {
        IDataField joinedField = joinedModel.getField( joinedItemEntry.getKey() );
        if( joinedItemEntry.getValue() == null || StringUtils.trimToNull( joinedItemEntry.getValue().toString() ) == null ) {
          continue;
        }
        query.addCriteria( new MatchCriteria( joinedTable.getColumn( joinedField.getTableField() ), joinedItemEntry.getValue() ) );
      }
    }
  }
  @Override
  public void fillSelectQueryResult( DataItem resultItem, String value, String fullField ) {
    //    String parts[] = ModelUtils.splitField( fullField );
    //    if( parts.length == 1 ) {
    //      IDataModel joinedModel = App.getModel( getAppObj() );
    //      DataItem joinedItem = new DataItem();
    //      joinedItem.addFieldValue( joinedModel.getPrimaryFieldName(), value );
    //      resultItem.addFieldValue( getFieldName(), joinedItem );
    //    } else {
    //      DataItem joinedItem = ( DataItem )resultItem.getFieldValue( parts[0] );
    //      if( joinedItem == null ) {
    //        joinedItem = new DataItem();
    //        resultItem.addFieldValue( parts[0], joinedItem );
    //      }
    //      joinedItem.addFieldValue( parts[1], value );
    //    }
    resultItem.addFieldValue( fullField, value );
  }
  @Override
  public void setSelectQueryOrder( SelectQuery query, String fullField, EColumnOrder order ) {
    Table table = query.getMainTable();
    String[] parts = ModelUtils.splitField( fullField );
    if( parts.length == 1 ) {
      query.orderBy( new ColumnOrder( table.getColumn( getTableField(), getFieldName() ), order ) );
    } else {
      IDataModel joinedModel = App.getModel( getAppObj() );
      IDataField joinedField = joinedModel.getField( parts[1] );
      Table joinedTable = new Table( joinedModel.getTableName(), joinedModel.getAppObj() );
      query.leftJoin( new Join( joinedTable, table.getColumn( getTableField(), getFieldName() ), joinedTable.getColumn( joinedModel.getPrimaryFieldName() ) ) );
      query.orderBy( new ColumnOrder( joinedTable.getColumn( joinedField.getTableField(), joinedField.getFieldName() ), order ) );
    }
  }
}
