package com.iks.cms.core.data;

import org.apache.commons.lang3.*;

import javax.xml.crypto.*;

/**
 * @author Igor Kaynov
 */
public class DataField implements IDataField {
  private String name;
  private String label;
  private String tableField;
  @Override
  public String getName() {
    return StringUtils.defaultIfBlank( name, tableField );
  }
  public void setName( String name ) {
    this.name = name;
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
}
