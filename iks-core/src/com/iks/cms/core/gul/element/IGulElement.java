package com.iks.cms.core.gul.element;

import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public interface IGulElement {
  String getTemplatePath();
  String getTemplateName();
  String getId();
  String getTag();
  void parse(IDataModel model, Element xmlElement) throws Exception;
}
