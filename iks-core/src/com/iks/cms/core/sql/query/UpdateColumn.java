package com.iks.cms.core.sql.query;

import com.iks.cms.core.sql.*;

public class UpdateColumn {
  private Column      column;
  private ColumnValue value;
  public UpdateColumn( Column column, Object value ) {
    this.column = column;
    this.value = new ColumnValue( value, column.getType() );
  }
  public Column getColumn() {
    return column;
  }
  public Object getValue() {
    return value.toString();
  }
  public String toString() {
    return column.getName() + "=" + value.toString();
  }
}