package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GulBox extends GulContainer {
  private String      orient = "horizontal";
  private EGulBoxPack pack   = EGulBoxPack.START;
  public String getTemplatePath() {
    return "gul/box";
  }
  public String getOrient() {
    return orient;
  }
  public void setOrient( String orient ) {
    this.orient = orient;
  }
  @Override
  public void parse( IDataModel model, Element xmlElement ) throws Exception {
    super.parse( model, xmlElement );
    if( xmlElement.hasAttribute( GulConstant.ATTR_ORIENT ) ) {
      setOrient( xmlElement.getAttribute( GulConstant.ATTR_ORIENT ) );
    }
    String packAttr = xmlElement.getAttribute( GulConstant.ATTR_PACK );
    if( packAttr != null ) {
      EGulBoxPack value = EGulBoxPack.getByValue( packAttr );
      if( value == null ) {
        throw new Exception( "Wrong " + GulConstant.ATTR_PACK + " attribute " + packAttr );
      }
      setPack( value );
    }
  }
  @Override
  public List< String > getCssClasses() {
    List< String > cssClasses = super.getCssClasses();
    cssClasses.add( Objects.equals( orient, "horizontal" ) ? "hbox" : "vbox" );
    cssClasses.add( "pack-" + pack.getValue() );
    return cssClasses;
  }
  public String getPack() {
    return pack.getValue();
  }
  public void setPack( EGulBoxPack pack ) {
    this.pack = pack;
  }
}
