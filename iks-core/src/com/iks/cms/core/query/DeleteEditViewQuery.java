package com.iks.cms.core.query;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.hibernate.*;
import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class DeleteEditViewQuery extends DeleteModelQuery {
  private static final Logger logger = LoggerFactory.getLogger( SelectEditViewQuery.class );
  private IEditView  editView;
  public DeleteEditViewQuery( IDataModel model, IEditView editView, Long itemId ) {
    super(model, itemId);
    this.editView = editView;
  }
}
