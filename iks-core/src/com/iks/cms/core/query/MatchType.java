package com.iks.cms.core.query;

/**
 * @author Igor Kaynov
 */
public enum MatchType {
  Eq( "=" ),
  Gt( ">" ),
  Lt( "<" ),
  Qe( ">=" ),
  Le( "<=" );
  private String condition;
  private MatchType( String condition ) {
    this.condition = condition;
  }
  public String getCondition() {
    return condition;
  }
  public String toString() {
    return condition;
  }
}
