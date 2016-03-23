package com.iks.cms.core.sql.query;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class SqlQuery {
  protected void appendList( StringBuilder sql, List list, String init, String sep, String end ) {
    boolean first = true;
    for( Object s : list ) {
      if( first ) {
        sql.append( init );
      } else {
        sql.append( sep );
      }
      sql.append( s == null ? null : s.toString() );
      first = false;
    }
    if (end != null) {
      sql.append( end );
    }
  }
}