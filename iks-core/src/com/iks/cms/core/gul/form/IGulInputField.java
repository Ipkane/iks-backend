package com.iks.cms.core.gul.form;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;

/**
 * @author Igor Kaynov
 */
public interface IGulInputField extends IGulElement {
  String getFieldName();
  String getLabel();
  Object getDefaultValue();
  void applyModel(IDataField dataField);
//  IFieldContainer getFieldContainer();
}
