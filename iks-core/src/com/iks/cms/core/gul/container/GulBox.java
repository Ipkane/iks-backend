package com.iks.cms.core.gul.container;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GulBox extends GulContainer {
  private String       orient = "horizontal";
  private EGulBoxPack  pack   = EGulBoxPack.START;
  private EGulBoxAlign align  = EGulBoxAlign.START;
  public String getTemplatePath() {
    return "gul/box";
  }
  @Override
  public String getTag() {
    return GulConstant.BOX;
  }
  public String getOrient() {
    return orient;
  }
  public void setOrient( String orient ) {
    this.orient = orient;
  }
  @Override
  public void parse(IParseContext context,  Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
    if( xmlElement.hasAttribute( GulConstant.ATTR_ORIENT ) ) {
      setOrient( xmlElement.getAttribute( GulConstant.ATTR_ORIENT ) );
    }
    String packAttr = StringUtils.trimToNull( xmlElement.getAttribute( GulConstant.ATTR_PACK ));
    if( packAttr != null ) {
      EGulBoxPack value = EGulBoxPack.getByValue( packAttr );
      if( value == null ) {
        throw new Exception( "Wrong " + GulConstant.ATTR_PACK + " attribute " + packAttr );
      }
      setPack( value );
    }
    String alignAttr = StringUtils.trimToNull( xmlElement.getAttribute( GulConstant.ATTR_ALIGN ) );
    if( alignAttr != null ) {
      EGulBoxAlign value = EGulBoxAlign.getByValue( alignAttr );
      if( value == null ) {
        throw new Exception( "Wrong " + GulConstant.ATTR_ALIGN + " attribute " + alignAttr );
      }
      setAlign( value );
    }
  }
  @Override
  public List< String > getCssClasses() {
    List< String > cssClasses = super.getCssClasses();
    cssClasses.add( Objects.equals( orient, "horizontal" ) ? "hbox" : "vbox" );
    cssClasses.add( "pack-" + pack.getValue() );
    cssClasses.add( "align-" + align.getValue() );
    return cssClasses;
  }
  public String getPack() {
    return pack.getValue();
  }
  public void setPack( EGulBoxPack pack ) {
    this.pack = pack;
  }
  public EGulBoxAlign getAlign() {
    return align;
  }
  public void setAlign( EGulBoxAlign align ) {
    this.align = align;
  }
}
