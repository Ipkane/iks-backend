package com.iks.cms.core.sql;

import com.iks.cms.core.query.*;

/**
 * @author Igor Kaynov
 */
public class MatchCriteria implements ICriteria {
  private Column column;
  private String condition;
  private Object value;
  public MatchCriteria( Column column, Object value, MatchType matchType ) {
    this.column = column;
    this.value = value;
    this.condition = matchType.getCondition();
  }
  public String toString() {
    return column.toString() + condition + "'" + value + "'";
  }
}
