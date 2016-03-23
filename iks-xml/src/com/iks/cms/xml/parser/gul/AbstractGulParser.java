package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public abstract class AbstractGulParser< T extends IGulElement > implements IGulParser< T > {
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
  }
}
