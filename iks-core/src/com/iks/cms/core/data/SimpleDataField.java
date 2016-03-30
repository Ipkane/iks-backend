package com.iks.cms.core.data;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.apache.commons.lang3.*;

/**
 * @author Igor Kaynov
 *
 * This is a simple table field, without references etc.
 */
public abstract class SimpleDataField extends AbstractDataField {
  @Override
  public void extendSelectQueryFields( SelectQuery query, String fullField ) {
    Table table = query.getMainTable();
    query.addColumn( table.getColumn( getTableField(), getFieldName() ) );
  }
  @Override
  public void extendSelectQueryFilter( SelectQuery query, Object value ) {
    Table table = query.getMainTable();
    if( value == null || StringUtils.trimToNull( value.toString() ) == null ) {
      return;
    }
    query.addCriteria( new MatchCriteria( table.getColumn( getFieldName() ), value ) );
  }
  @Override
  public void fillSelectQueryResult(DataItem resultItem, String value, String fullField) {
    resultItem.addFieldValue( getFieldName(), parseValue( value ) );
  }
}
