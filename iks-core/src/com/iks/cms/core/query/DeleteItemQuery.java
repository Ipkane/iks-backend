package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public class DeleteItemQuery {
  private IEditView  editView;
  private IDataModel model;
  private Long       itemId;
  public DeleteItemQuery( IDataModel model, IEditView editView, Long itemId ) {
    this.model = model;
    this.editView = editView;
    this.itemId = itemId;
  }
  public String buildSqlQuery() {
    if( itemId == null ) {
      throw new IllegalArgumentException( "Item id is null" );
    }
    StringBuilder sb = new StringBuilder();
    sb.append( "delete from " ).append( "\"" ).append( model.getTableName() ).append( "\"" );
    sb.append( " where id=" ).append( itemId );
    return sb.toString();
  }
}
