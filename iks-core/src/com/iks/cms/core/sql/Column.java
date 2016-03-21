package com.iks.cms.core.sql;

/**
 * @author Igor Kaynov
 */
public class Column {
  private String     tableAlias;
  private String     name;
  private String     alias;
  private ColumnType type = ColumnType.String;
  public Column( String name ) {
    this.name = name;
  }
  public Column( Table table, String name ) {
    this( table.getAlias(), name, null );
  }
  public Column( Table table, String name, String alias ) {
    this( table.getAlias(), name, alias );
  }
  public Column( String tableAlias, String name, String alias ) {
    this( name );
    this.tableAlias = tableAlias;
    this.alias = alias;
  }
  public String getName() {
    return name;
  }
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if( tableAlias != null ) {
      sb.append( tableAlias ).append( '.' );
    }
    sb.append( name );
    if( alias != null ) {
      sb.append( " as " ).append( alias );
    }
    return sb.toString();
  }
  public ColumnType getType() {
    return type;
  }
  public void setType( ColumnType type ) {
    this.type = type;
  }
}
