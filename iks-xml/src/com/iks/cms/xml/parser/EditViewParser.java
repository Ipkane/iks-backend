package com.iks.cms.xml.parser;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class EditViewParser extends CommonParser {
  private static final Logger logger = LoggerFactory.getLogger( EditViewParser.class );
 private IDataModel model;
  public EditViewParser( IDataModel model ) {
    this.model = model;
  }
  public EditView parse( String fileName ) throws Exception {
    Document doc = parseFile( fileName );
    return parseRoot( doc );
  }
  private EditView parseRoot( Document doc ) throws Exception {
    Element root = doc.getDocumentElement();
    EditView editView = new EditView();
    NodeList fieldList = root.getChildNodes();
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element fieldElement = ( Element )node;
        switch( fieldElement.getTagName() ) {
          case "field":
            editView.addElement( parseField( fieldElement ) );
            break;
          default:
            throw new Exception( "Couldn't parse element " + fieldElement.getTagName() );
        }
      }
    }
    return editView;
  }
  private IGulElement parseField( Element fieldElement ) {
    GulInput field = new GulInput();
    field.setName( fieldElement.getAttribute( "name" ) );
    DataField dataField = ( DataField )model.getField( field.getName() );
    if( fieldElement.hasAttribute( "label" ) ) {
      field.setLabel( fieldElement.getAttribute( "label" ) );
    } else {
      field.setLabel( dataField.getLabel() );
    }
    if( fieldElement.hasAttribute( "required" ) ) {
      field.setRequired( BooleanUtils.toBoolean( fieldElement.getAttribute( "required" ) ) );
    } else {
      field.setRequired( dataField.isRequired() );
    }
    return field;
  }
}
