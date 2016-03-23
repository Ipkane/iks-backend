package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public interface IGulParser<T extends IGulElement> {
  void parse(IDataModel model, T gulElement, Element xmlElement) throws Exception;
}
