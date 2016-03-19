package com.iks.cms.core.model;

import com.iks.cms.core.data.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IDataModel {
  IDataField getField(String name);
  List<IDataField> getFields();
  String getTableName();
}
