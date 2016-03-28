package com.iks.cms.core.sql.projection;

import com.iks.cms.core.sql.*;

/**
 * @author Igor Kaynov
 */
public class Projection implements IProjection {
  private String query;
  private Column column;
  public Projection( String query, Column column ) {
    this.query = query;
    this.column = column;
  }
  public String getQuery() {
    return query;
  }
  public void setQuery( String query ) {
    this.query = query;
  }
  @Override
  public String toString() {
    return query + "(" + (column == null ? "*" : column) + ")";
  }
}
