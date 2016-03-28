package com.iks.cms.core.parser;

import com.iks.cms.core.constant.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ModelParser {
  private static final Logger logger = LoggerFactory.getLogger( ModelParser.class );
  public IDataModel parse( String fileName ) throws Exception {
    Document doc = ParserUtils.parseFile( fileName );
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
          case ModelConstant.SELECT_FIELD:
            model.addField( parseSelectField( fieldElement ) );
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
  // field
  private IDataField parseSimpleField( Element fieldElement ) {
    InputDataField field = new InputDataField();
    fillDataField( field, fieldElement );
    if( fieldElement.hasAttribute( ModelConstant.PRIMARY_KEY_ATTR ) ) {
      field.setIsPrimaryKey( BooleanUtils.toBoolean( fieldElement.getAttribute( ModelConstant.PRIMARY_KEY_ATTR ) ) );
    }
    if( fieldElement.hasAttribute( ModelConstant.TYPE_ATTR ) ) {
      field.setType( EDataType.getByValue( fieldElement.getAttribute( ModelConstant.TYPE_ATTR ) ) );
    }
    return field;
  }
  private IDataField parseSelectField( Element fieldElement ) {
    SelectField field = new SelectField();
    fillDataField( field, fieldElement );
    fillSelectOptions( field, fieldElement );
    return field;
  }
  //manyToOne
  private ManyToOne parseManyToOneField( Element fieldElement ) {
    ManyToOne field = new ManyToOne();
    fillDataField( field, fieldElement );
    field.setAppObj( fieldElement.getAttribute( ModelConstant.APP_OBJ_ATTR ) );
    if( fieldElement.hasAttribute( ModelConstant.REFERENCE_FIELD_ATTR ) ) {
      field.setReferenceField( fieldElement.getAttribute( ModelConstant.REFERENCE_FIELD_ATTR ) );
    }
    return field;
  }
  private void fillDataField( AbstractDataField field, Element fieldElement ) {
    field.setName( fieldElement.getAttribute( ModelConstant.NAME_ATTR ) );
    field.setLabel( fieldElement.getAttribute( ModelConstant.LABEL_ATTR ) );
    if( fieldElement.hasAttribute( ModelConstant.REQUIRED_ATTR ) ) {
      field.setRequired( BooleanUtils.toBoolean( fieldElement.getAttribute( ModelConstant.REQUIRED_ATTR ) ) );
    }
    field.setTableField( fieldElement.getAttribute( ModelConstant.TABLE_FIELD_ATTR ) );
  }
  private void fillSelectOptions( SelectField field, Element fieldElement ) {
    Element optionsElement = ( Element )fieldElement.getElementsByTagName( ModelConstant.OPTIONS ).item( 0 );
    NodeList optionsList = optionsElement.getElementsByTagName( ModelConstant.OPTION );
    for( int i = 0; i < optionsList.getLength(); i++ ) {
      Element optionElement = ( Element )optionsList.item( i );
      SelectOption option = new SelectOption();
      option.setValue( optionElement.getAttribute( ModelConstant.OPTION_VALUE_ATTR ) );
      option.setLabel( optionElement.getTextContent() );
      field.addOption( option );
    }
  }
}
