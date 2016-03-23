package com.iks.cms.core.model;

import com.google.common.collect.*;

import com.iks.cms.core.data.*;
import com.iks.cms.core.exception.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.validation.*;

import org.apache.commons.lang3.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class BaseDataModel implements IDataModel {
  private String appObj;
  private String tableName;
  private List< IDataField > fields     = new ArrayList<>();
  private List< IValidator > validators = new ArrayList<>();
  public List< IDataField > getFields() {
    return fields;
  }
  public void setFields( List< IDataField > fields ) {
    this.fields = fields;
  }
  public void addField( IDataField field ) {
    this.fields.add( field );
  }
  @Override
  public String getTableName() {
    return tableName;
  }
  public void setTableName( String tableName ) {
    this.tableName = tableName;
  }
  @Override
  public String getPrimaryFieldName() {
    return FieldConstant.DEFAULT_PRIMARY_FIELD;
  }
  @Override
  public IDataField getField( String name ) {
    return fields.stream().filter( field -> field.getName().equals( name ) ).findFirst().orElse( null );
  }
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  @Override
  public boolean validate( IDataItem item ) {
    DataItem dataItem = ( DataItem )item;
    boolean valid = true;
    for (IValidator validator: getAllValidators()) {
      if (!validator.validate( this, item )) {
        dataItem.addError(validator.getError());
        valid = false;
      }
    }
    return valid;
  }
  protected List< IValidator > getAllValidators() {
    List< IValidator > validators = new ArrayList<>();
    validators.addAll( this.validators );
    for( IDataField dataField : fields ) {
      validators.addAll( dataField.getValidators() );
    }
    return validators;
  }
}
