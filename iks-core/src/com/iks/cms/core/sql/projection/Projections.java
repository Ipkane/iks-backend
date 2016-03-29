package com.iks.cms.core.sql.projection;

import com.iks.cms.core.sql.*;

/**
 * @author Igor Kaynov
 */
public class Projections {
  public static IProjection rowCount(Column column) {
    return new Projection( "count", column );
  }
  public static IProjection rowCount() {
    return new Projection( "count", null );
  }
}
