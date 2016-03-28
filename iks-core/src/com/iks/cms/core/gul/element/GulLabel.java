package com.iks.cms.core.gul.element;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulLabel extends GulElement {
  private String value;
  private String control;
  @Override
  public String getTag() {
    return GulConstant.LABEL;
  }
  @Override
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
    setValue( xmlElement.getTextContent() );
    if( xmlElement.hasAttribute( GulConstant.ATTR_VALUE ) ) {
      setValue( xmlElement.getAttribute( GulConstant.ATTR_VALUE ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_CONTROL ) ) {
      setControl( xmlElement.getAttribute( GulConstant.ATTR_CONTROL ) );
    }
  }
  public String getValue() {
    return value;
  }
  public void setValue( String value ) {
    this.value = value;
  }
  public String getControl() {
    return control;
  }
  public void setControl( String control ) {
    this.control = control;
  }
}
