package com.iks.cms.core.sql.join;

import com.iks.cms.core.sql.*;

/**
 * @author Igor Kaynov
 */
public class Join {
  private Table         table;
  private Column firstColumn;
  private Column secondColumn;
  private JoinType joinType = JoinType.Left;
  public Join(Table table, Column firstColumn, Column secondColumn) {
    this.table = table;
    this.firstColumn = firstColumn;
    this.secondColumn = secondColumn;
  }

  public Table getTable() {
    return table;
  }
  public void setTable( Table table ) {
    this.table = table;
  }
  public JoinType getJoinType() {
    return joinType;
  }
  public void setJoinType( JoinType joinType ) {
    this.joinType = joinType;
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(  );
    sb.append( table ).append( " on " ).append( firstColumn ).append( "=" ).append( secondColumn );
    return sb.toString();
  }
  public Column getFirstColumn() {
    return firstColumn;
  }
  public void setFirstColumn( Column firstColumn ) {
    this.firstColumn = firstColumn;
  }
  public Column getSecondColumn() {
    return secondColumn;
  }
  public void setSecondColumn( Column secondColumn ) {
    this.secondColumn = secondColumn;
  }
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Join)) {
      return false;
    }
    Join otherJoin = (Join) o;
    return table.equals(  otherJoin.table )
        && firstColumn.equals(otherJoin.firstColumn )
        && secondColumn.equals( otherJoin.secondColumn )
        && joinType.equals( otherJoin.joinType );
  }
}
