package com.iks.cms.core.gul.element;

import com.google.common.base.*;

import com.fasterxml.jackson.annotation.*;
import com.iks.cms.common.json.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class GulElement implements IGulElement {
  @JsonView( JsonViews.Normal.class )
  protected String        id;
  private   String        style;
  private   String        cssClass;
  private   String        flex;
  @JsonIgnore
  private   IGulContainer parent;
  @Override
  public String getId() {
    return id;
  }
  public void setId( String id ) {
    this.id = id;
  }
  @Override
  public String getTemplatePath() {
    return "gul/element";
  }
  @Override
  public abstract String getTag();
  @Override
  public String getTemplateName() {
    return getTag();
  }
  public String getStyle() {
    StringBuilder sb = new StringBuilder();
    if( flex != null ) {
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
  public String getFlex() {
    return flex;
  }
  public void setFlex( String flex ) {
    this.flex = flex;
  }
  @Override
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    if( xmlElement.hasAttribute( GulConstant.ATTR_ID ) ) {
      setId( xmlElement.getAttribute( GulConstant.ATTR_ID ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_STYLE ) ) {
      setStyle( xmlElement.getAttribute( GulConstant.ATTR_STYLE ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_FLEX ) ) {
      setFlex( xmlElement.getAttribute( GulConstant.ATTR_FLEX ) );
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
  @Override
  public IGulContainer getParent() {
    return parent;
  }
  @Override
  public void setParent( IGulContainer parent ) {
    this.parent = parent;
  }
}
