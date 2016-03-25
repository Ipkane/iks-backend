package com.iks.cms.core.gul.element;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulHtml extends GulElement {
  private String content;
  public String getContent() {
    return content;
  }
  public void setContent( String content ) {
    this.content = content;
  }
  @Override
  public String getTag() {
    return "markup";
  }
  @Override
  public void parse( Element xmlElement ) throws Exception {
    super.parse( xmlElement );
    setContent( xmlElement.getTextContent() );
  }
}
