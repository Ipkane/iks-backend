package com.iks.cms.core.sql.query;

import com.iks.cms.core.sql.*;

/**
 * @author Igor Kaynov
 */
public class ColumnOrder {
  private Column       column;
  private EColumnOrder order;
  public ColumnOrder( Column column, EColumnOrder order ) {
    this.column = column;
    this.order = order;
  }
  public EColumnOrder getOrder() {
    return order;
  }
  public void setOrder( EColumnOrder order ) {
    this.order = order;
  }
  public Column getColumn() {
    return column;
  }
  public void setColumn( Column column ) {
    this.column = column;
  }
  @Override
  public String toString() {
    return column.toString() + " " + order.toString();
  }
}
