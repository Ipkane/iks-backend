package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public class EditViewQuery {
  private IEditView  editView;
  private IDataModel model;
  private Long itemId;
  public EditViewQuery( IDataModel model, IEditView editView ) {
    this.model = model;
    this.editView = editView;
  }
  public String buildSqlQuery() {
    StringBuilder sb = new StringBuilder();
    sb.append( "select " );
    boolean first = true;
    for( IGulInput field : editView.getFields() ) {
      IDataField dataField = model.getField( field.getName() );
      if( !first ) {
        sb.append( "," );
      }
      sb.append( "\"" ).append( dataField.getTableField() ).append( "\"" ).append( " as " ).append( dataField.getName() );
      first = false;
    }
    sb.append( " from " ).append( "\"" ).append( model.getTableName() ).append( "\"" );
    sb.append( " where id=" ).append( itemId );
    return sb.toString();
  }
  public Long getItemId() {
    return itemId;
  }
  public void setItemId( Long itemId ) {
    this.itemId = itemId;
  }
}
