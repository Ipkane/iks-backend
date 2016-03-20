package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class UpdateItemQuery {
  private IEditView  editView;
  private IDataModel model;
  private IDataRow   item;
  private Long       itemId;
  public UpdateItemQuery( IDataModel model, IEditView editView, IDataRow item ) {
    this.model = model;
    this.editView = editView;
    setItem( item );
  }
  public String buildSqlQuery() {
    if( itemId == null ) {
      throw new IllegalArgumentException( "Item id is null" );
    }
    StringBuilder sb = new StringBuilder();
    sb.append( "update " ).append( "\"" ).append( model.getTableName() ).append( "\"" ).append( " set " );
    boolean first = true;
    for( IGulInput field : editView.getFields() ) {
      IDataField dataField = model.getField( field.getName() );
      if( dataField.getName().equals( "id" ) ) {
        continue;
      }
      if( !first ) {
        sb.append( "," );
      }
      sb.append( "\"" ).append( dataField.getTableField() ).append( "\"" ).append( "=" ).append( "'" ).append( item.getFieldValue( dataField.getName() ) ).append( "'" );
      first = false;
    }
    sb.append( " where id=" ).append( itemId );
    return sb.toString();
  }
  public IDataRow getItem() {
    return item;
  }
  public void setItem( IDataRow item ) {
    this.item = item;
    itemId = Long.valueOf( item.getFieldValue( "id" ).toString() );
  }
}
