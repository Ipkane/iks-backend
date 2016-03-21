package com.iks.cms.xml.parser;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.w3c.dom.*;

import java.io.*;

import javax.xml.parsers.*;

/**
 * @author Igor Kaynov
 */
public class GridParser extends CommonParser {
  private static final Logger logger = LoggerFactory.getLogger( GridParser.class );
  private IDataModel model;
  public GridParser( IDataModel model ) {
    this.model = model;
  }
  public IGrid parse( String fileName ) throws Exception {
    Document doc = parseFile( fileName );
    return parseRoot( doc );
  }
  private IGrid parseRoot( Document doc ) {
    Element root = ( Element )doc.getDocumentElement().getElementsByTagName( "table" ).item( 0 );
    Grid grid = new Grid();
    NodeList fieldList = doc.getElementsByTagName( "field" );
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element fieldElement = ( Element )node;
        grid.addField( parseField( fieldElement ) );
      }
    }
    return grid;
  }
  // field
  private IGridField parseField( Element fieldElement ) {
    GridField field = new GridField();
    field.setName( fieldElement.getAttribute( "name" ) );
    DataField dataField = ( DataField )model.getField( field.getName() );
    if( fieldElement.hasAttribute( "label" ) ) {
      field.setLabel( fieldElement.getAttribute( "label" ) );
    } else {
      field.setLabel( dataField.getLabel() );
    }
    return field;
  }
}
