package com.iks.cms.core.sql;

/**
 * @author Igor Kaynov
 */
public class Table {
  private String name;
  private String alias;
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
}
