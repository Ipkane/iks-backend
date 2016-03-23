package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class InputFieldParser<T extends GulInputField> extends ElementParser<T> {
  private static InputFieldParser instance;
  protected InputFieldParser() {
  }
  public static InputFieldParser getInstance() {
    if( instance == null ) {
      instance = new InputFieldParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    gulElement.setName( xmlElement.getAttribute( EditConstant.NAME_ATTR ) );
    DataField dataField = ( DataField )model.getField( gulElement.getName() );
    if( xmlElement.hasAttribute( EditConstant.LABEL_ATTR ) ) {
      gulElement.setLabel( xmlElement.getAttribute( EditConstant.LABEL_ATTR ) );
    } else {
      gulElement.setLabel( dataField.getLabel() );
    }
    if( xmlElement.hasAttribute( EditConstant.REQUIRED_ATTR ) ) {
      gulElement.setRequired( BooleanUtils.toBoolean( xmlElement.getAttribute( EditConstant.REQUIRED_ATTR ) ) );
    } else {
      gulElement.setRequired( dataField.isRequired() );
    }
    if( xmlElement.hasAttribute( EditConstant.READONLY_ATTR ) ) {
      gulElement.setReadonly( BooleanUtils.toBoolean( xmlElement.getAttribute( EditConstant.READONLY_ATTR ) ) );
    }
  }
}
