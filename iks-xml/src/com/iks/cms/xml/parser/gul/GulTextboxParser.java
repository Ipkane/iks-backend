package com.iks.cms.xml.parser.gul;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulTextboxParser<T extends GulTextbox > extends GulElementParser<T> {
  private static GulTextboxParser instance;
  protected GulTextboxParser() {
  }
  public static GulTextboxParser getInstance() {
    if( instance == null ) {
      instance = new GulTextboxParser();
    }
    return instance;
  }
  @Override
  public void parse( IDataModel model, T gulElement, Element xmlElement ) throws Exception {
    super.parse( model, gulElement, xmlElement );
    gulElement.setName( xmlElement.getAttribute( GulConstant.NAME_ATTR ) );
    DataField dataField = ( DataField )model.getField( gulElement.getName() );
    if( xmlElement.hasAttribute( GulConstant.LABEL_ATTR ) ) {
      gulElement.setLabel( xmlElement.getAttribute( GulConstant.LABEL_ATTR ) );
    } else {
      gulElement.setLabel( dataField.getLabel() );
    }
    if( xmlElement.hasAttribute( GulConstant.REQUIRED_ATTR ) ) {
      gulElement.setRequired( BooleanUtils.toBoolean( xmlElement.getAttribute( GulConstant.REQUIRED_ATTR ) ) );
    } else {
      gulElement.setRequired( dataField.isRequired() );
    }
    if( xmlElement.hasAttribute( GulConstant.READONLY_ATTR ) ) {
      gulElement.setReadonly( BooleanUtils.toBoolean( xmlElement.getAttribute( GulConstant.READONLY_ATTR ) ) );
    }
  }
}
