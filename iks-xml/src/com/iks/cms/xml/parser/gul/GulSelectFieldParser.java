package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulSelectFieldParser<T extends GulSelect > extends GulTextboxParser<T> {
  private static GulSelectFieldParser instance;
  protected GulSelectFieldParser() {
  }
  public static GulSelectFieldParser getInstance() {
    if( instance == null ) {
      instance = new GulSelectFieldParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    SelectField dataField = ( SelectField )model.getField( gulElement.getName() );
    gulElement.setOptions( dataField.getOptions() );
  }
  private void fillSelectOptions( GulSelect select, Element fieldElement ) {
    Element optionsElement = ( Element )fieldElement.getElementsByTagName( GulConstant.OPTIONS ).item( 0 );
    NodeList optionsList = optionsElement.getElementsByTagName( GulConstant.OPTION );
    for( int i = 0; i < optionsList.getLength(); i++ ) {
      Element optionElement = ( Element )optionsList.item( i );
      SelectOption option = new SelectOption();
      option.setValue( optionElement.getAttribute( GulConstant.OPTION_VALUE_ATTR ) );
      option.setLabel( optionElement.getTextContent() );
      select.addOption( option );
    }
  }
}
