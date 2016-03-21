package com.iks.cms.core.sql.query;

import com.iks.cms.core.sql.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class UpdateQuery extends SqlQuery {
  private Table table;
  private List< UpdateColumn > updateColumns = new ArrayList<>();
  private List< ICriteria >    criterias     = new ArrayList<>();
  public void addUpdateColumn( Column column, Object value ) {
    updateColumns.add( new UpdateColumn( column, value ) );
  }
  public void addCriteria(ICriteria criteria) {
    criterias.add( criteria );
  }
  @Override
  public String toString() {
    StringBuilder sql = new StringBuilder( "update " );
    sql.append( table.getName() ).append( " set " );
    appendList( sql, updateColumns, "", ", " );
    appendList( sql, criterias, " where ", " and " );
    return sql.toString();
  }
  public void setTable( Table table ) {
    this.table = table;
  }
  private static class UpdateColumn {
    private Column column;
    private Object value;
    public UpdateColumn( Column column, Object value ) {
      this.column = column;
      this.value = value;
    }
    public String toString() {
      return column.getName() + "=" + "'" + value + "'";
    }
  }
}
