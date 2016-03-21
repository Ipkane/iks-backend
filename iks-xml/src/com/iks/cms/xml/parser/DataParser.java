package com.iks.cms.xml.parser;

import com.iks.cms.core.data.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;

import javax.xml.parsers.*;

/**
 * @author Igor Kaynov
 */
public class DataParser extends CommonParser {
  private static final Logger logger = LoggerFactory.getLogger( DataParser.class );
  public IDataModel parse( String fileName ) throws Exception {
    Document doc = parseFile( fileName );
    return parseRoot( doc );
  }
  private IDataModel parseRoot( Document doc ) {
    Element root = doc.getDocumentElement();
    BaseDataModel model = new BaseDataModel();
    model.setAppObj( root.getAttribute( "appObj" ) );
    model.setTableName( root.getAttribute( "tableName" ) );
    NodeList fieldList = doc.getElementsByTagName( "field" );
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element fieldElement = ( Element )node;
        model.addField( parseField( fieldElement ) );
      }
    }
    return model;
  }
  private IDataField parseField( Element fieldElement ) {
    DataField field = new DataField();
    field.setName( fieldElement.getAttribute( "name" ) );
    field.setLabel( fieldElement.getAttribute( "label" ) );
    field.setTableField( fieldElement.getAttribute( "tableField" ) );
    if( fieldElement.hasAttribute( "primary" ) ) {
      field.setIsPrimaryKey( BooleanUtils.toBoolean( fieldElement.getAttribute( "primary" ) ) );
    }
    if( fieldElement.hasAttribute( "required" ) ) {
      field.setRequired( BooleanUtils.toBoolean( fieldElement.getAttribute( "required" ) ) );
    }
    return field;
  }
}
