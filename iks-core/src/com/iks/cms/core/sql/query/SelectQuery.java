package com.iks.cms.core.sql.query;

import com.iks.cms.core.sql.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class SelectQuery  extends SqlQuery {
  private List< Column >    columns   = new ArrayList<>();
  private List< Table >     tables    = new ArrayList<>();
  private List< String >    joins     = new ArrayList<>();
  private List< String >    leftJoins = new ArrayList<>();
  private List< ICriteria > criterias = new ArrayList<>();
  private List< String >    orderBys  = new ArrayList<>();
  private List< Column >    groupBys  = new ArrayList<>();
  private List< String >    havings   = new ArrayList<>();
  public SelectQuery() {
  }
  public SelectQuery( Table table ) {
    tables.add( table );
  }
  public SelectQuery addColumn( Column column ) {
    columns.add( column );
    return this;
  }
  public SelectQuery addColumn( Column column, boolean groupBy ) {
    columns.add( column );
    if( groupBy ) {
      groupBys.add( column );
    }
    return this;
  }
  public SelectQuery from( Table table ) {
    tables.add( table );
    return this;
  }
  public SelectQuery groupBy( Column column ) {
    groupBys.add( column );
    return this;
  }
  public SelectQuery having( String expr ) {
    havings.add( expr );
    return this;
  }
  public SelectQuery join( String join ) {
    joins.add( join );
    return this;
  }
  public SelectQuery leftJoin( String join ) {
    leftJoins.add( join );
    return this;
  }
  public SelectQuery orderBy( String name ) {
    orderBys.add( name );
    return this;
  }
  @Override
  public String toString() {
    StringBuilder sql = new StringBuilder( "select " );
    if( columns.size() == 0 ) {
      sql.append( "*" );
    } else {
      appendList( sql, columns, "", ", " );
    }
    appendList( sql, tables, " from ", ", " );
    appendList( sql, joins, " join ", " join " );
    appendList( sql, leftJoins, " left join ", " left join " );
    appendList( sql, criterias, " where ", " and " );
    appendList( sql, groupBys, " group by ", ", " );
    appendList( sql, havings, " having ", " and " );
    appendList( sql, orderBys, " order by ", ", " );
    return sql.toString();
  }
  public SelectQuery addCriteria( ICriteria criteria ) {
    criterias.add( criteria );
    return this;
  }
}
