package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public class GridQuery {
  private IGrid      grid;
  private IDataModel model;
  public GridQuery( IDataModel model, IGrid grid ) {
    this.model = model;
    this.grid = grid;
  }
  public IGrid getGrid() {
    return grid;
  }
  public void setGrid( IGrid grid ) {
    this.grid = grid;
  }
  public String buildSqlQuery() {
    StringBuilder sb = new StringBuilder();
    sb.append( "select " );
    boolean first = true;
    for( IGridField field : grid.getFields() ) {
      IDataField dataField = model.getField( field.getName() );
      if( !first ) {
        sb.append( "," );
      }
      sb.append( dataField.getTableField() ).append( " as " ).append( dataField.getName() );
      first = false;
    }
    sb.append( " from " ).append( model.getTableName() );
    return sb.toString();
  }
}
