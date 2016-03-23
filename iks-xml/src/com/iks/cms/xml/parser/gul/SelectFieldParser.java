package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class SelectFieldParser<T extends GulSelectField> extends InputFieldParser<T> {
  private static SelectFieldParser instance;
  protected SelectFieldParser() {
  }
  public static SelectFieldParser getInstance() {
    if( instance == null ) {
      instance = new SelectFieldParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    SelectField dataField = ( SelectField )model.getField( gulElement.getName() );
    gulElement.setOptions( dataField.getOptions() );
  }
  private void fillSelectOptions( GulSelectField select, Element fieldElement ) {
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
}
