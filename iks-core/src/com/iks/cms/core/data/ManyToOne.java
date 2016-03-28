package com.iks.cms.core.data;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.constant.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ManyToOne extends AbstractDataField {
  private String appObj;
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  /**
   * Primary field of referenced model
   * @return
   */
  public String getReferenceField() {
    return App.getModel(appObj).getPrimaryFieldName();
  }
  @Override
  public void parse(Element xmlElement) {
    super.parse(xmlElement);
    setAppObj( xmlElement.getAttribute( ModelConstant.APP_OBJ_ATTR ) );
  }
}
