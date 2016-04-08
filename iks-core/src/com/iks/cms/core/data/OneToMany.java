package com.iks.cms.core.data;

import com.iks.cms.core.appObj.App;
import com.iks.cms.core.constant.ModelConstant;
import com.iks.cms.core.grid.DataItem;
import com.iks.cms.core.model.IDataModel;
import com.iks.cms.core.sql.Table;
import com.iks.cms.core.sql.join.Join;
import com.iks.cms.core.sql.query.ColumnOrder;
import com.iks.cms.core.sql.query.EColumnOrder;
import com.iks.cms.core.sql.query.SelectQuery;
import com.iks.cms.core.utils.ModelUtils;
import org.w3c.dom.Element;

/**
 * @author Igor Kaynov
 */
public class OneToMany extends AbstractDataField {
  private String appObj;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
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
    IDataModel otherModel = App.getModel( getAppObj() );
    Table otherTable = new Table( otherModel.getTableName(), otherModel.getAppObj() );
    query.leftJoin( new Join( otherTable, table.getColumn( FieldConstant.DEFAULT_PRIMARY_FIELD ), otherTable.getColumn( getTableField() ) ) );
    IDataField otherField;
    if( parts.length == 1 ) {
      otherField = otherModel.getField( otherModel.getPrimaryFieldName() );
    } else {
      otherField = otherModel.getField( parts[1] );
    }
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
  @Override
  public void setSelectQueryOrder( SelectQuery query, String fullField, EColumnOrder order ) {
    Table table = query.getMainTable();
    String[] parts = ModelUtils.splitField( fullField );
    IDataModel otherModel = App.getModel( getAppObj() );
    Table otherTable = new Table( otherModel.getTableName(), otherModel.getAppObj() );
    query.leftJoin( new Join( otherTable, table.getColumn( FieldConstant.DEFAULT_PRIMARY_FIELD ), otherTable.getColumn( getTableField() ) ) );
    IDataField otherField;
    otherField = otherModel.getField( parts[1] );
    query.orderBy( new ColumnOrder( otherTable.getColumn( otherField.getTableField(), otherField.getFieldName() ), order ) );
  }
}
