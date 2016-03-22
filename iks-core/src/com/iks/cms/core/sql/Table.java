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
    return alias != null ? name + " as " + alias : name;
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
}
