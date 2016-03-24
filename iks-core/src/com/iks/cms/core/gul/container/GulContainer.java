package com.iks.cms.core.gul.container;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class GulContainer extends GulElement implements IGulContainer {
  private List< IGulElement > elements = new ArrayList<>();
  @Override
  public List< IGulElement > getElements() {
    return elements;
  }
  public void setElements( List< IGulElement > elements ) {
    this.elements = elements;
  }
  public void addElement( IGulElement element ) {
    elements.add( element );
  }
  @Override
  public void parse( IDataModel model, Element parentXmlElement ) throws Exception {
    super.parse( model, parentXmlElement );
    NodeList fieldList = parentXmlElement.getChildNodes();
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element xmlElement = ( Element )node;
        IGulElement gulElement = GulFactory.createElement( xmlElement.getTagName() );
        gulElement.parse( model, xmlElement );
        addElement( gulElement );
      }
    }
  }
}
