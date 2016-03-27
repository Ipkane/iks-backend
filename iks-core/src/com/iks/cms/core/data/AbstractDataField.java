package com.iks.cms.core.data;

import com.iks.cms.core.validation.*;

import org.apache.commons.lang3.*;

import java.util.*;
import java.util.stream.*;

import javax.xml.crypto.*;

/**
 * @author Igor Kaynov
 */
public abstract class AbstractDataField implements IDataField {
  private String name;
  private String label;
  private String tableField;
  private boolean            required   = false;
  private List< IValidator > validators = new ArrayList<>();
  @Override
  public String getName() {
    return StringUtils.defaultIfBlank( name, tableField );
  }
  public void setName( String name ) {
    this.name = name;
    if (tableField == null) {
      tableField = name;
    }
  }
  @Override
  public String getLabel() {
    if( StringUtils.isBlank( label ) ) {
      label = StringUtils.capitalize( getName() );
    }
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
  @Override
  public String getTableField() {
    return tableField;
  }
  public void setTableField( String tableField ) {
    this.tableField = tableField;
  }
  public boolean isRequired() {
    return required;
  }
  public void setRequired( boolean required ) {
    this.required = required;
    if( required ) {
      addValidator( new RequiredValidator( name ) );
    } else {
      removeValidator( RequiredValidator.class );
    }
  }
  @Override
  public List< IValidator > getValidators() {
    return validators;
  }
  @Override
  public Object parseValue( String value ) {
    return value;
  }
  public void setValidators( List< IValidator > validators ) {
    this.validators = validators;
  }
  public void addValidator( IValidator newValidator ) {
    for( IValidator validator : validators ) {
      if( validator.getClass().isAssignableFrom( newValidator.getClass() ) ) {
        return;
      }
    }
    this.validators.add( newValidator );
  }
  public < T extends IValidator > void removeValidator( Class< T > validatorClass ) {
    validators.removeIf( validator -> validator.getClass().isAssignableFrom( validatorClass ) );
  }
}
