package com.iks.cms.core.model;

import com.google.common.collect.*;

import com.iks.cms.core.data.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class BaseDataModel implements IDataModel {
  private String appObj;
  private String tableName;
  private List< IDataField > fields = new ArrayList<>();
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
  public IDataField getField( String name ) {
    return fields.stream().filter( field -> field.getName().equals( name ) ).findFirst().orElse( null );
  }
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
}
