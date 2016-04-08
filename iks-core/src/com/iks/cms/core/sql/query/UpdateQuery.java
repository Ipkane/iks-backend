package com.iks.cms.core.sql.query;

import com.iks.cms.core.constant.ModelConstant;
import com.iks.cms.core.data.FieldConstant;
import com.iks.cms.core.query.MatchType;
import com.iks.cms.core.sql.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class UpdateQuery extends AbstractChangeQuery {
  private List< ICriteria >    criterias     = new ArrayList<>();
  public void addUpdateColumn( Column column, Object value ) {
    updateColumns.add( new UpdateColumn( column, value ) );
  }
  public void addCriteria(ICriteria criteria) {
    criterias.add( criteria );
  }
  @Override
  public String toString() {
    StringBuilder sql = new StringBuilder( "update " );
    sql.append( table.getName() ).append( " set " );
    appendList( sql, updateColumns, "", ", ", " " );
    appendList( sql, criterias, " where ", " and ", " " );
    return sql.toString();
  }
  public void setCriteriaById(Long itemId) {
    criterias.add(new MatchCriteria(table.getColumn(FieldConstant.DEFAULT_PRIMARY_FIELD), itemId, MatchType.Eq ) );
  }
}
