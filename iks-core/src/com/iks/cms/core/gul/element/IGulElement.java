package com.iks.cms.core.gul.element;

import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public interface IGulElement {
  String getTemplatePath();
  String getTemplateName();
  String getId();
  String getTag();
  IGulContainer getParent() ;
  void setParent(IGulContainer parent);
  void parse( IParseContext context, Element xmlElement) throws Exception;
}
