package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulContainerParser<T extends GulContainer> extends GulElementParser< T > {
  private static GulContainerParser instance;
  protected GulContainerParser() {
  }
  public static GulContainerParser getInstance() {
    if( instance == null ) {
      instance = new GulContainerParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T parentGulElement, Element parentXmlElement ) throws Exception {
    super.parse( model, parentGulElement, parentXmlElement );
    NodeList fieldList = parentXmlElement.getChildNodes();
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        GulElement gulElement;
        Element xmlElement = ( Element )node;
        switch( xmlElement.getTagName() ) {
          case GulConstant.TEXTBOX: {
            gulElement = new GulTextbox();
            GulTextboxParser.getInstance().parse( model, ( GulTextbox )gulElement, xmlElement );
            break;
          }
          case GulConstant.SELECT: {
            gulElement = new GulSelect();
            GulSelectFieldParser.getInstance().parse( model, ( GulSelect )gulElement, xmlElement );
            break;
          }
          case GulConstant.REFERENCE_SELECT: {
            gulElement = new GulReferenceSelect();
            GulReferenceSelectFieldParser.getInstance().parse( model, ( GulReferenceSelect )gulElement, xmlElement );
            break;
          }
          case GulConstant.BUTTON:
            gulElement = new GulButton();
            GulButtonParser.getInstance().parse( model, ( GulButton )gulElement, xmlElement );
            break;
          case GulConstant.BOX:
            gulElement = new GulBox();
            GulBoxParser.getInstance().parse( model, ( GulBox )gulElement, xmlElement );
            break;
          case GulConstant.HBOX:
            gulElement = new GulHBox();
            GulHBoxParser.getInstance().parse( model, ( GulHBox )gulElement, xmlElement );
            break;
          case GulConstant.VBOX:
            gulElement = new GulVBox();
            GulVBoxParser.getInstance().parse( model, ( GulVBox )gulElement, xmlElement );
            break;
          default:
            throw new Exception( "Couldn't parse element " + xmlElement.getTagName() );
        }
        parentGulElement.addElement( gulElement );
      }
    }
  }
}
