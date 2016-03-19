package com.iks.cms.xml.parser;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;

import org.slf4j.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class EditViewParser extends CommonParser {
  private static final Logger logger = LoggerFactory.getLogger( EditViewParser.class );
  public EditView parse( String fileName ) throws Exception {
    Document doc = parseFile( fileName );
    return parseRoot( doc );
  }
  private EditView parseRoot( Document doc ) throws Exception {
    Element root = ( Element )doc.getDocumentElement();
    EditView editView = new EditView();
    NodeList fieldList = root.getChildNodes();
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element fieldElement = ( Element )node;
        switch( fieldElement.getTagName() ) {
          case "field":
            editView.addElement( parseField( fieldElement ));
            break;
          default:
            throw new Exception( "Couldn't parse element " + fieldElement.getTagName() );
        }
      }
    } return editView;
  }
  private IGulElement parseField( Element fieldElement ) {
    GulInput field = new GulInput();
    field.setName( fieldElement.getAttribute( "name" ) );
    field.setLabel( fieldElement.getAttribute( "label" ) );
    return field;
  }
}
