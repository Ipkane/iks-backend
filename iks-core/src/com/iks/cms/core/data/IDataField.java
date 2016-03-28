package com.iks.cms.core.data;

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
}
