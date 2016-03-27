package com.iks.cms.core.data;

/**
 * @author Igor Kaynov
 */
public class ManyToOne extends AbstractDataField {
  private String appObj;
  private String referenceField = FieldConstant.DEFAULT_PRIMARY_FIELD;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  public String getReferenceField() {
    return referenceField;
  }
  public void setReferenceField( String referenceField ) {
    this.referenceField = referenceField;
  }
}
