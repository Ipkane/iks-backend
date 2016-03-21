package com.iks.cms.core.gul;

/**
 * @author Igor Kaynov
 */
public interface IGulInputField extends IGulElement{
  String getName();
  String getLabel();
  String getType();
  Object getDefaultValue();
}
