package com.iks.cms.xml.parser;

import com.iks.cms.core.data.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;

import javax.xml.crypto.*;
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
  private IDataModel parseRoot( Document doc ) throws Exception {
    Element root = doc.getDocumentElement();
    BaseDataModel model = new BaseDataModel();
    model.setAppObj( root.getAttribute( ModelConstant.APP_OBJ_ATTR ) );
    model.setTableName( root.getAttribute( ModelConstant.TABLE_NAME_ATTR ) );
    NodeList fieldList = root.getChildNodes();
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element fieldElement = ( Element )node;
        switch( fieldElement.getTagName() ) {
          case ModelConstant.FIELD:
            model.addField( parseSimpleField( fieldElement ) );
            break;
          case ModelConstant.MANY_TO_ONE:
            model.addField( parseManyToOneField( fieldElement ) );
            break;
          default:
            throw new Exception( "Couldn't parse element " + fieldElement.getTagName() );
        }
      }
    }
    return model;
  }
  private IDataField parseSimpleField( Element fieldElement ) {
    SimpleDataField field = new SimpleDataField();
    fillDataField( field, fieldElement );
    if( fieldElement.hasAttribute( ModelConstant.PRIMARY_KEY_ATTR ) ) {
      field.setIsPrimaryKey( BooleanUtils.toBoolean( fieldElement.getAttribute( ModelConstant.PRIMARY_KEY_ATTR ) ) );
    }
    return field;
  }
  private ManyToOne parseManyToOneField( Element fieldElement ) {
    ManyToOne field = new ManyToOne();
    fillDataField( field, fieldElement );
    field.setAppObj( fieldElement.getAttribute( ModelConstant.APP_OBJ_ATTR ) );
    if( fieldElement.hasAttribute( ModelConstant.REFERENCE_FIELD_ATTR ) ) {
      field.setReferenceField( fieldElement.getAttribute( ModelConstant.REFERENCE_FIELD_ATTR ) );
    }
    return field;
  }
  private void fillDataField( DataField field, Element fieldElement ) {
    field.setName( fieldElement.getAttribute( ModelConstant.NAME_ATTR ) );
    field.setLabel( fieldElement.getAttribute( ModelConstant.LABEL_ATTR ) );
    if( fieldElement.hasAttribute( ModelConstant.REQUIRED_ATTR ) ) {
      field.setRequired( BooleanUtils.toBoolean( fieldElement.getAttribute( ModelConstant.REQUIRED_ATTR ) ) );
    }
    field.setTableField( fieldElement.getAttribute( ModelConstant.TABLE_FIELD_ATTR ) );
  }
}
