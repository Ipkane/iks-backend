package com.iks.cms.core.data;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.sql.query.*;
import com.iks.cms.core.validation.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IDataField<T> {
  String getName();

  String getLabel();

  String getTableField();

  boolean isRequired();

  List<IValidator> getValidators();

  void parse(Element xmlElement);

  T parseValue(String value);

  void extendSelectQueryFields( SelectQuery query, String fullField );
  void extendSelectQueryFilter( SelectQuery query, Object value );
  void fillSelectQueryResult(DataItem resultItem, String value, String fullField);
}
