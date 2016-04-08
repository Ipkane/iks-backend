package com.iks.cms.core.query.model;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.*;
import com.iks.cms.core.query.editView.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class UpdateModelQuery< T extends UpdateModelQuery > extends AbstractChangeModelQuery< T > {
  private static final Logger logger = LoggerFactory.getLogger( UpdateEditViewQuery.class );
  public UpdateModelQuery( IDataModel model, IDataItem item ) {
    super( model, item );
  }
  @Override
  protected UpdateQuery buildSqlQuery() {
    UpdateQuery sb = new UpdateQuery();
    fillQuery( sb );
    Table table = sb.getTable();
    sb.setCriteriaById(getItemId());
    return sb;
  }
  public Long getItemId() {
    return Long.valueOf( item.getFieldValue( model.getPrimaryFieldName() ).toString() );
  }
}
