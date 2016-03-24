package com.iks.cms.core.gul.container;

import com.iks.cms.core.gul.form.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IFieldContainer extends IGulContainer {
  List<IGulInputField > getFields();
  void addField(IGulInputField field);
  void removeField(IGulInputField field);
}
