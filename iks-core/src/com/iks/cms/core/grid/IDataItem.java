package com.iks.cms.core.grid;

import com.iks.cms.core.exception.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IDataItem {
  Object getFieldValue(String fieldName);
  List<IValidationError> getErrors();
}
