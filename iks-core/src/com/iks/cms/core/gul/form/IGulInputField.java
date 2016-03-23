package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;

/**
 * @author Igor Kaynov
 */
public interface IGulInputField extends IGulElement {
  String getName();
  String getLabel();
  Object getDefaultValue();
}
