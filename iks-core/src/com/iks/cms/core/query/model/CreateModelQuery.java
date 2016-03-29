package com.iks.cms.core.query.model;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.query.*;

import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class CreateModelQuery<T extends CreateModelQuery> extends AbstractChangeModelQuery<T> {
  private static final Logger logger = LoggerFactory.getLogger( CreateModelQuery.class );
  public CreateModelQuery( IDataModel model, IDataItem item ) {
    super( model, item );
  }
  @Override
  protected InsertQuery buildSqlQuery() {
    InsertQuery sb = new InsertQuery();
    fillQuery( sb );
    return sb;
  }
}
