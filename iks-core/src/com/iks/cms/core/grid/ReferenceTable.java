package com.iks.cms.core.grid;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ReferenceTable extends BaseGrid {
  private String fieldName;
  private String label;
  public ReferenceTable() {
    id = UUID.randomUUID().toString();
    //    showToolbar = false;
  }
  public String getFieldName() {
    return fieldName;
  }
  public void setFieldName( String fieldName ) {
    this.fieldName = fieldName;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
  @Override
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    setAppObj( context.getAppObj() );
    super.parse( context, xmlElement );
    if( xmlElement.hasAttribute( GulConstant.ATTR_FIELD_NAME ) ) {
      setFieldName( xmlElement.getAttribute( GulConstant.ATTR_FIELD_NAME ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_LABEL ) ) {
      setLabel( xmlElement.getAttribute( GulConstant.ATTR_LABEL ) );
    }
  }
}
