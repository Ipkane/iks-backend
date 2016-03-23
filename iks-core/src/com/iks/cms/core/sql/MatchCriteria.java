package com.iks.cms.core.sql;

import com.iks.cms.core.query.*;

import org.apache.commons.lang3.*;

/**
 * @author Igor Kaynov
 */
public class MatchCriteria implements ICriteria {
  private Column column;
  private String condition;
  private Object value;
  public MatchCriteria( Column column, Object value ) {
    this(column, value, MatchType.Eq);
  }
  public MatchCriteria( Column column, Object value, MatchType matchType ) {
    this.column = column;
    this.value = value;
    this.condition = matchType.getCondition();
  }
  public String toString() {
    String valueStr = value == null ? null : StringUtils.trimToNull( value.toString() );
    if (valueStr == null) {
      return column.toString() + " is null";
    } else {
      return column.toString() + condition + "'" + value + "'";
    }
  }
}
