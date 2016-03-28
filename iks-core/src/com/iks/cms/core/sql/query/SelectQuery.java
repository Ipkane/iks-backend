package com.iks.cms.core.sql.query;

import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.join.*;
import com.iks.cms.core.sql.projection.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class SelectQuery extends SqlQuery {
  private List< Column >      columns     = new ArrayList<>();
  private List< IProjection > projections = new ArrayList<>();
  private List< Table >       tables      = new ArrayList<>();
   private List< Join >        leftJoins   = new ArrayList<>();
  private List< ICriteria >   criterias   = new ArrayList<>();
  private List< ColumnOrder > orderBys    = new ArrayList<>();
  private List< Column >      groupBys    = new ArrayList<>();
  private List< String >      havings     = new ArrayList<>();
  private Integer limit = 0;
  private Integer offset = 0;
  public SelectQuery() {
  }
  public SelectQuery( Table table ) {
    tables.add( table );
  }
  public SelectQuery addColumn( Column column ) {
    columns.add( column );
    return this;
  }
  public List<Column> getColumns() {
    return columns;
  }
  public SelectQuery addColumn( Column column, boolean groupBy ) {
    columns.add( column );
    if( groupBy ) {
      groupBys.add( column );
    }
    return this;
  }
  public SelectQuery addProjection(IProjection projection) {
    projections.add( projection );
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
  public SelectQuery leftJoin( Join join ) {
    if( !leftJoins.contains( join ) ) {
      leftJoins.add( join );
    }
    return this;
  }
  public SelectQuery orderBy( ColumnOrder columnOrder ) {
    orderBys.add( columnOrder );
    return this;
  }
  @Override
  public String toString() {
    StringBuilder sql = new StringBuilder( "select " );
    appendList( sql, columns, " ", ", ", "" );
    appendList( sql, projections, " ", ", ", "" );
    if( columns.size() == 0 && projections.size() == 0 ) {
      sql.append( "*" );
    }
    appendList( sql, tables, " from ", ", ", "" );
    appendList( sql, leftJoins, " left join ", " left join ", "" );
    appendList( sql, criterias, " where ", " and ", "" );
    appendList( sql, groupBys, " group by ", ", ", "" );
    appendList( sql, havings, " having ", " and ", "" );
    appendList( sql, orderBys, " order by ", ", ", "" );
    if( limit > 0 ) {
      sql.append( " limit " + limit );
    }
    if( offset > 0 ) {
      sql.append( " offset " + offset );
    }
    return sql.toString();
  }
  public SelectQuery addCriteria( ICriteria criteria ) {
    criterias.add( criteria );
    return this;
  }
  public Integer getOffset() {
    return offset;
  }
  public void setOffset( Integer offset ) {
    this.offset = offset == null ? 0 : offset;
  }
  public Integer getLimit() {
    return limit;
  }
  public void setLimit( Integer limit ) {
    this.limit = limit == null ? 0 : limit;
  }
  public Table getMainTable() {
    return tables.get( 0 );
  }
  public List<Join> getLeftJoins() {
    return leftJoins;
  }
  public List<ColumnOrder > getOrderBys() {
    return orderBys;
  }
}
