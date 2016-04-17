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
public class UpdateModelQuery<T extends UpdateModelQuery> extends AbstractChangeModelQuery<T> {
  private static final Logger logger = LoggerFactory.getLogger(UpdateEditViewQuery.class);
  private Long itemId;

  public UpdateModelQuery(IDataModel model, IDataItem item, Long itemId) {
    super(model, item);
    this.itemId = itemId;
  }

  @Override
  protected UpdateQuery buildSqlQuery() {
    UpdateQuery sb = new UpdateQuery(new Table(model.getTableName()));
    fillQuery(sb);
    sb.setCriteriaById(getItemId());
    return sb;
  }

  public Long getItemId() {
    return itemId;
  }
}
