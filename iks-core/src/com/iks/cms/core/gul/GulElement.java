package com.iks.cms.core.gul;

import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public abstract class GulElement implements IGulElement {
  protected String id;
  private String tag;
  @Override
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  @Override
  public abstract String getTemplatePath();
  @Override
  public String getTag() {
    return tag;
  }
  public void setTag( String tag ) {
    this.tag = tag;
  }
  @Override
  public String getTemplateName() {
    return getTag();
  }
  @Override
  public void parse( IDataModel model, Element xmlElement ) throws Exception {
    setTag( xmlElement.getTagName() );
    setId( xmlElement.getAttribute( GulConstant.LABEL_ATTR ) );
  }
}
