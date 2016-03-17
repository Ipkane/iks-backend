package com.iks.cms.core.model;

import com.iks.cms.core.data.*;

/**
 * @author Igor Kaynov
 */
public interface IDataModel {
  IDataField getField(String name);
  String getTableName();
}
