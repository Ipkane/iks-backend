package com.iks.cms.core.gul;

import com.google.common.base.*;

import com.iks.cms.core.model.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class GulElement implements IGulElement {
  protected String id;
  private   String style;
  private   String cssClass;
  private   String tag;
  private   int    flex;
  @Override
  public String getId() {
    return id;
  }
  public void setId( String id ) {
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
  public String getStyle() {
    StringBuilder sb = new StringBuilder();
    if( flex > 0 ) {
      sb.append( "flex:" + flex );
    }
    if( style != null ) {
      sb.append( style );
    }
    return sb.length() == 0 ? "" : sb.toString();
  }
  public void setStyle( String style ) {
    this.style = style;
  }
  public int getFlex() {
    return flex;
  }
  public void setFlex( int flex ) {
    this.flex = flex;
  }
  @Override
  public void parse( IDataModel model, Element xmlElement ) throws Exception {
    setTag( xmlElement.getTagName() );
    setId( xmlElement.getAttribute( GulConstant.ATTR_LABEL ) );
    if( xmlElement.hasAttribute( GulConstant.ATTR_STYLE ) ) {
      setStyle( xmlElement.getAttribute( GulConstant.ATTR_STYLE ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_FLEX ) ) {
      setFlex( Integer.valueOf( xmlElement.getAttribute( GulConstant.ATTR_FLEX ) ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_CSS_CLASS ) ) {
      setCssClass( xmlElement.getAttribute( GulConstant.ATTR_CSS_CLASS ) );
    }
  }
  public String getCssClass() {
    return Joiner.on( " " ).skipNulls().join( getCssClasses() );
  }
  public List< String > getCssClasses() {
    List< String > cssClasses = new ArrayList<>();
    if( cssClass != null ) {
      cssClasses.add( cssClass );
    }
    return cssClasses;
  }
  public void setCssClass( String cssClass ) {
    this.cssClass = cssClass;
  }
}
