package com.iks.cms.core.sql;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class Table {
  private String name;
  private String alias;
  private Map< String, Column > columns = new HashMap<>();
  public Table( String name ) {
    this.name = name;
  }
  public Table( String name, String alias ) {
    this( name );
    this.alias = alias;
  }
  public String toString() {
    return alias != null ? SqlUtils.quote(name) + " as " + SqlUtils.quote( alias ) : SqlUtils.quote( name );
  }
  public String getAlias() {
    return alias;
  }
  public String getName() {
    return name;
  }
  public Column getColumn( String fieldName ) {
    return getColumn( fieldName, null );
  }
  public Column getColumn( String fieldName, String fieldAlias ) {
    Column column = columns.get( fieldName );
    if( column == null ) {
      column = new Column( this, fieldName, fieldAlias );
      columns.put( fieldName, column );
    }
    return column;
  }
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Table)) {
      return false;
    }
    Table otherTable = (Table) o;
    return Objects.equals(  name, otherTable.name);
  }
}
