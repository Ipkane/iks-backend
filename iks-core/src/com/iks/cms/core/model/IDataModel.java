package com.iks.cms.core.model;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IDataModel {
  String getPrimaryFieldName();
  IDataField getField(String name);
  List<IDataField> getFields();
  List<String> getFieldNames();
  String getTableName();
  boolean validate( IDataItem item );
}
