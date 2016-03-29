package com.iks.cms.core.query.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.editView.*;
import com.iks.cms.core.query.model.*;

import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class DeleteGridQuery extends DeleteModelQuery {
  private static final Logger logger = LoggerFactory.getLogger( SelectEditViewQuery.class );
  private IBaseGrid grid;
  public DeleteGridQuery( IDataModel model, IBaseGrid grid, Long itemId ) {
    super( model, itemId );
    this.grid = grid;
  }
}
