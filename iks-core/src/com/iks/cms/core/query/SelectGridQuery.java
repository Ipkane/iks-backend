package com.iks.cms.core.query;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class SelectGridQuery extends SelectModelQuery<SelectGridQuery> {
  private static final Logger logger = LoggerFactory.getLogger( SelectGridQuery.class );
  private IGrid grid;

  public SelectGridQuery( IDataModel model, IGrid grid ) {
    super( model );
    this.grid = grid;
    for( IGridColumn field : grid.getFields() ) {
      addField( field.getName() );
      if (field.getDisplayField() != null) {
        addField( field.getName() + Constants.FIELD_SEPARATOR + field.getDisplayField() );
      }
    }
  }
  public IGrid getGrid() {
    return grid;
  }
  public void setGrid( IGrid grid ) {
    this.grid = grid;
  }
}
