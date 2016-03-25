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
  protected List< IGulElement > elements = new ArrayList<>();
//  protected IGulContainer root;
  public GulContainer() {
    super();
//    root = this;
//    elements.add( root );
  }
  @Override
  public List< IGulElement > getElements() {
    return elements;
  }
  public void setElements( List< IGulElement > elements ) {
    this.elements.clear();
    for( IGulElement element : elements ) {
      addElement( element );
    }
  }
  public void addElement( IGulElement element ) {
    element.setParent(this);
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
        addElement( gulElement );
        gulElement.parse( model, xmlElement );
      }
    }
  }
//  public IGulContainer getRoot() {
//      return root;
//    }
//  public void setRoot( IGulContainer root ) {
//    this.root = root;
//  }
}
