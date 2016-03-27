package com.iks.cms.core.sql.query;

import com.iks.cms.core.sql.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class InsertQuery extends AbstractChangeQuery {
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

}
