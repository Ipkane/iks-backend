package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ContainerParser extends ElementParser< GulContainer > {
  private static ContainerParser instance;
  protected ContainerParser() {
  }
  public static ContainerParser getInstance() {
    if( instance == null ) {
      instance = new ContainerParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, GulContainer parentGulElement, Element parentXmlElement ) throws Exception {
    super.parse( model, parentGulElement, parentXmlElement );
    NodeList fieldList = parentXmlElement.getChildNodes();
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        GulElement gulElement;
        Element xmlElement = ( Element )node;
        switch( xmlElement.getTagName() ) {
          case EditConstant.FIELD: {
            gulElement = new GulInputField();
            InputFieldParser.getInstance().parse( model, ( GulInputField )gulElement, xmlElement );
            break;
          }
          case EditConstant.SELECT_FIELD: {
            gulElement = new GulSelectField();
            SelectFieldParser.getInstance().parse( model, ( GulSelectField )gulElement, xmlElement );
            break;
          }
          case EditConstant.REFERENCE_SELECT_FIELD: {
            gulElement = new GulReferenceSelectField();
            ReferenceSelectFieldParser.getInstance().parse( model, ( GulReferenceSelectField )gulElement, xmlElement );
            break;
          }
          case EditConstant.BUTTON:
            gulElement = new GulButton();
            ButtonParser.getInstance().parse( model, ( GulButton )gulElement, xmlElement );
            break;
          default:
            throw new Exception( "Couldn't parse element " + xmlElement.getTagName() );
        }
        parentGulElement.addElement( gulElement );
      }
    }
  }
}
