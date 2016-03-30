package com.iks.cms.core.data;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.constant.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.join.*;
import com.iks.cms.core.sql.query.*;
import com.iks.cms.core.utils.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ManyToMany extends AbstractDataField {
  private String appObj;
  private String joinTable;
  private String inverseTableField;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  public String getJoinTable() {
    return joinTable;
  }
  public void setJoinTable( String joinTable ) {
    this.joinTable = joinTable;
  }
  public String getInverseTableField() {
    return inverseTableField;
  }
  public void setInverseTableField( String inverseTableField ) {
    this.inverseTableField = inverseTableField;
  }
  @Override
  public void parse( Element xmlElement ) {
    super.parse( xmlElement );
    setAppObj( xmlElement.getAttribute( ModelConstant.APP_OBJ_ATTR ) );
    setJoinTable( xmlElement.getAttribute( ModelConstant.ATTR_JOIN_TABLE ) );
    setInverseTableField( xmlElement.getAttribute( ModelConstant.ATTR_INVERSE_TABLE_FIELD ) );
  }
  @Override
  public void extendSelectQueryFields( SelectQuery query, String fullField ) {
    Table table = query.getMainTable();
    String[] parts = ModelUtils.splitField( fullField );
    IDataModel otherModel = App.getModel( getAppObj() );
    Table otherTable = new Table( otherModel.getTableName(), otherModel.getAppObj() );
    Table joinTable = new Table( getJoinTable(), getJoinTable() );
    query.leftJoin( new Join( joinTable, table.getColumn( FieldConstant.DEFAULT_PRIMARY_FIELD ), joinTable.getColumn( getTableField() ) ) );
    IDataField otherField;
    if( parts.length == 1 ) {
      otherField = otherModel.getField( otherModel.getPrimaryFieldName() );
    } else {
      otherField = otherModel.getField( parts[1] );
    }
    query.leftJoin( new Join( otherTable, joinTable.getColumn( getInverseTableField() ), otherTable.getColumn( otherModel.getPrimaryFieldName() ) ) );
    query.addColumn( otherTable.getColumn( otherField.getTableField(), otherField.getFieldName() ) );
  }
  @Override
  public void extendSelectQueryFilter( SelectQuery query, Object value ) {
    throw new RuntimeException( "Method not implemented" );
  }
  @Override
  public void fillSelectQueryResult( DataItem resultItem, String value, String fullField ) {
    String parts[] = ModelUtils.splitField( fullField );
    DataItem joinedItem = ( DataItem )resultItem.getFieldValue( parts[0] );
    if( joinedItem == null ) {
      joinedItem = new DataItem();
      resultItem.addFieldValue( parts[0], joinedItem );
    }
    joinedItem.addFieldValue( parts[1], value );
  }
}
