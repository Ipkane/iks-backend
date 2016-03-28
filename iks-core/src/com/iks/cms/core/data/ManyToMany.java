package com.iks.cms.core.data;

import com.iks.cms.core.constant.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ManyToMany extends AbstractDataField {
  private String appObj;
  private String joinTable;
  private String inverseTableField;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  public String getJoinTable() {
    return joinTable;
  }
  public void setJoinTable( String joinTable ) {
    this.joinTable = joinTable;
  }
  public String getInverseTableField() {
    return inverseTableField;
  }
  public void setInverseTableField( String inverseTableField ) {
    this.inverseTableField = inverseTableField;
  }
  @Override
  public void parse( Element xmlElement ) {
    super.parse( xmlElement );
    setAppObj( xmlElement.getAttribute( ModelConstant.APP_OBJ_ATTR ) );
    setJoinTable( xmlElement.getAttribute( ModelConstant.ATTR_JOIN_TABLE ) );
    setInverseTableField( xmlElement.getAttribute( ModelConstant.ATTR_INVERSE_TABLE_FIELD ) );
  }
}
