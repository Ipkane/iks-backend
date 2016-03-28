package com.iks.cms.core.gul.element;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulScript extends GulElement {
  private String text;
  private String src;
  @Override
  public String getTag() {
    return "script";
  }
  @Override
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
    setText( xmlElement.getTextContent() );
    if( xmlElement.hasAttribute( GulConstant.ATTR_SRC ) ) {
      setSrc( xmlElement.getAttribute( GulConstant.ATTR_SRC ) );
    }
  }
  public String getText() {
    return text;
  }
  public void setText( String text ) {
    this.text = text;
  }
  public String getSrc() {
    return src;
  }
  public void setSrc( String src ) {
    this.src = src;
  }
}
