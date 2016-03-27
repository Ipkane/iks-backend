package com.iks.cms.core.data;

import com.iks.cms.core.validation.*;

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

  T parseValue(String value);
}
