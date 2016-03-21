package com.iks.cms.core.sql.query;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class SqlQuery {
  protected void appendList( StringBuilder sql, List list, String init, String sep ) {
    boolean first = true;
    for( Object s : list ) {
      if( first ) {
        sql.append( init );
      } else {
        sql.append( sep );
      }
      sql.append( s.toString() );
      first = false;
    }
  }
}
