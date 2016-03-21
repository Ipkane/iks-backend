package com.iks.cms.core.sql.query;

import com.iks.cms.core.sql.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class InsertQuery extends SqlQuery {
  private Table table;
  private List< UpdateColumn > updateColumns = new ArrayList<>();
  public void addUpdateColumn( Column column, Object value ) {
    updateColumns.add( new UpdateColumn( column, value ) );
  }
  @Override
  public String toString() {
    List< Column > columns = new ArrayList<>();
    List< Object > values = new ArrayList<>();
    for( UpdateColumn updateColumn : updateColumns ) {
      columns.add( updateColumn.getColumn() );
      values.add( updateColumn.getValue() );
    }
    StringBuilder sql = new StringBuilder( "insert into " );
    sql.append( table.getName() );
    appendList( sql, columns, " (", ", ", ") " );
    sql.append( "values" );
    appendList( sql, values, " (", ", ", ") " );
    return sql.toString();
  }
  public void setTable( Table table ) {
    this.table = table;
  }
}
