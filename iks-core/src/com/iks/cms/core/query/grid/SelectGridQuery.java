package com.iks.cms.core.query.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.*;
import com.iks.cms.core.query.model.*;

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
    for( IGridColumn field : grid.getColumns() ) {
      addField( field.getFieldName() );
      if( field.getDisplayField() != null ) {
        addField( field.getFieldName() + Constants.FIELD_SEPARATOR + field.getDisplayField() );
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
    if( StringUtils.isNotBlank( parentId ) ) {
      addFilter( model.getPrimaryFieldName(), getParentId() );
    }
  }
}
