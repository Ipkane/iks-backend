package com.iks.cms.xml.parser;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

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
          case EditConstant.FIELD:
            editView.addElement( parseField( fieldElement ) );
            break;
          case EditConstant.REFERENCE_SELECT_FIELD:
            editView.addElement( parseReferenceSelectField( fieldElement ) );
            break;
          default:
            throw new Exception( "Couldn't parse element " + fieldElement.getTagName() );
        }
      }
    }
    return editView;
  }
  // field
  private IGulElement parseField( Element fieldElement ) {
    GulInputField field;
    if( fieldElement.getElementsByTagName( EditConstant.OPTIONS ).getLength() > 0 ) {
      field = new GulSelect();
      fillSelectOptions( ( GulSelect )field, fieldElement );
    } else {
      field = new GulInputField();
    }
    fillInputField( field, fieldElement );
    return field;
  }
  private void fillInputField( GulInputField field, Element fieldElement ) {
    field.setName( fieldElement.getAttribute( EditConstant.NAME_ATTR ) );
    DataField dataField = ( DataField )model.getField( field.getName() );
    if( fieldElement.hasAttribute( EditConstant.LABEL_ATTR ) ) {
      field.setLabel( fieldElement.getAttribute( EditConstant.LABEL_ATTR ) );
    } else {
      field.setLabel( dataField.getLabel() );
    }
    if( fieldElement.hasAttribute( EditConstant.REQUIRED_ATTR ) ) {
      field.setRequired( BooleanUtils.toBoolean( fieldElement.getAttribute( EditConstant.REQUIRED_ATTR ) ) );
    } else {
      field.setRequired( dataField.isRequired() );
    }
    if( fieldElement.hasAttribute( EditConstant.READONLY_ATTR ) ) {
      field.setReadonly( BooleanUtils.toBoolean( fieldElement.getAttribute( EditConstant.READONLY_ATTR ) ) );
    }
  }
  private void fillSelectOptions( GulSelect select, Element fieldElement ) {
    Element optionsElement = ( Element )fieldElement.getElementsByTagName( EditConstant.OPTIONS ).item( 0 );
    NodeList optionsList = optionsElement.getElementsByTagName( EditConstant.OPTION );
    for( int i = 0; i < optionsList.getLength(); i++ ) {
      Element optionElement = ( Element )optionsList.item( i );
      SelectOption option = new SelectOption();
      option.setValue( optionElement.getAttribute( EditConstant.OPTION_VALUE_ATTR ) );
      option.setLabel( optionElement.getTextContent() );
      select.addOption( option );
    }
  }
  // field
  private GulReferenceField parseReferenceSelectField( Element fieldElement ) {
    GulReferenceSelectField field = new GulReferenceSelectField();
    fillInputField( field, fieldElement );
    field.setDisplayField( fieldElement.getAttribute( EditConstant.DISPLAY_FIELD_ATTR ) );
    return field;
  }
}
