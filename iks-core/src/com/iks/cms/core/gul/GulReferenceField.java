package com.iks.cms.core.gul;

/**
 * @author Igor Kaynov
 */
public class GulReferenceField extends GulInputField {
  private String displayField;
  public String getDisplayField() {
    return displayField;
  }
  public void setDisplayField( String displayField ) {
    this.displayField = displayField;
  }
  @Override
  public String getType() {
    return GulConstant.REFERENCE_TYPE;
  }
}
