package com.iks.cms.core.query;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class SelectGridQuery extends SelectModelQuery< SelectGridQuery > {
  private static final Logger logger = LoggerFactory.getLogger( SelectGridQuery.class );
  private String    parentId;
  private IBaseGrid grid;
  public SelectGridQuery( IDataModel model, IBaseGrid grid ) {
    super( model );
    this.grid = grid;
    String fieldPrefix = "";
    if( StringUtils.isNotBlank( grid.getParentAppObj() ) ) {
      IDataModel parentModel = App.getModel( grid.getParentAppObj() );
      IDataField parentField = parentModel.getField( grid.getName() );
      fieldPrefix = grid.getName() + ".";
      setModel( parentModel );
    }
    for( IGridColumn field : grid.getFields() ) {
      addField( fieldPrefix + field.getName() );
      if( field.getDisplayField() != null ) {
        addField( fieldPrefix + field.getName() + Constants.FIELD_SEPARATOR + field.getDisplayField() );
      }
    }
  }
  public IBaseGrid getGrid() {
    return grid;
  }
  public void setGrid( IBaseGrid grid ) {
    this.grid = grid;
  }
  public String getParentId() {
    return parentId;
  }
  public void setParentId( String parentId ) {
    this.parentId = parentId;
    IDataModel parentModel = App.getModel( grid.getParentAppObj() );
    if( StringUtils.isNotBlank( parentId ) ) {
      addFilter( parentModel.getPrimaryFieldName(), getParentId() );
    }
  }
}
