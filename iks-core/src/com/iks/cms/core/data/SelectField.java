package com.iks.cms.core.data;

import com.iks.cms.core.constant.*;
import com.iks.cms.core.gul.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class SelectField extends SimpleDataField {
  private List< SelectOption > options = new ArrayList<>();
  public List< SelectOption > getOptions() {
    return options;
  }
  public void setOptions( List< SelectOption > options ) {
    this.options = options;
  }
  public void addOption( SelectOption option ) {
    options.add( option );
  }
  @Override
  public void parse( Element xmlElement ) {
    super.parse( xmlElement );
    Element optionsElement = ( Element )xmlElement.getElementsByTagName( ModelConstant.OPTIONS ).item( 0 );
    NodeList optionsList = optionsElement.getElementsByTagName( ModelConstant.OPTION );
    for( int i = 0; i < optionsList.getLength(); i++ ) {
      Element optionElement = ( Element )optionsList.item( i );
      SelectOption option = new SelectOption();
      option.setValue( optionElement.getAttribute( ModelConstant.OPTION_VALUE_ATTR ) );
      option.setLabel( optionElement.getTextContent() );
      addOption( option );
    }
  }
}
