package com.iks.cms.core.grid;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.iks.cms.core.appObj.*;
import com.iks.cms.core.constant.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.panel.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

import sun.security.x509.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ReferenceTable extends BaseGrid {
  private String name;
  private String label;
  public ReferenceTable() {
    id = UUID.randomUUID().toString();
    showToolbar = false;
  }
  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
  @Override
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    setParentAppObj( context.getAppObj() );
    super.parse( context, xmlElement );
    if( xmlElement.hasAttribute( GulConstant.ATTR_NAME ) ) {
      setName( xmlElement.getAttribute( GulConstant.ATTR_NAME ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_LABEL ) ) {
      setLabel( xmlElement.getAttribute( GulConstant.ATTR_LABEL ) );
    }
  }
}
