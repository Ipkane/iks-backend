package com.iks.cms.core.sql.query;

import com.iks.cms.core.sql.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class DeleteQuery extends SqlQuery {
  private Table table;
  private List< ICriteria > criterias = new ArrayList<>();
  public void addCriteria( ICriteria criteria ) {
    criterias.add( criteria );
  }
  @Override
  public String toString() {
    StringBuilder sql = new StringBuilder( "delete from " );
    sql.append( table.getName() );
    appendList( sql, criterias, " where ", " and " );
    return sql.toString();
  }
  public void setTable( Table table ) {
    this.table = table;
  }
}
