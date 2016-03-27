package com.iks.cms.core.sql.query;

import com.iks.cms.core.sql.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class AbstractChangeQuery extends SqlQuery {
  protected Table table;
  protected List< UpdateColumn > updateColumns = new ArrayList<>();
  public void addUpdateColumn( Column column, Object value ) {
    updateColumns.add( new UpdateColumn( column, value ) );
  }
  public Table getTable() {
    return table;
  }
  public void setTable( Table table ) {
    this.table = table;
  }
}
