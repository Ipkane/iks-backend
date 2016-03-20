package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public class CreateItemQuery {
  private IEditView  editView;
  private IDataModel model;
  private IDataRow   item;
  public CreateItemQuery( IDataModel model, IEditView editView, IDataRow item ) {
    this.model = model;
    this.editView = editView;
    setItem( item );
  }
  public String buildSqlQuery() {
    StringBuilder sb = new StringBuilder();
    sb.append( "insert into " ).append( "\"" ).append( model.getTableName() ).append( "\"" ).append( " (" );
    boolean first = true;
    StringBuilder values = new StringBuilder(  );
    for( IGulInput field : editView.getFields() ) {
      IDataField dataField = model.getField( field.getName() );
      if( dataField.getName().equals( "id" ) ) {
        continue;
      }
      if( !first ) {
        sb.append( "," );
        values.append(",");
      }
      sb.append( "\"" ).append( dataField.getTableField() ).append( "\"" );
      values.append( "'" ).append( item.getFieldValue( dataField.getName() ) ).append( "'" );
      first = false;
    }
    sb.append(") values (").append(values).append(")");
    return sb.toString();
  }
  public IDataRow getItem() {
    return item;
  }
  public void setItem( IDataRow item ) {
    this.item = item;
  }
}
