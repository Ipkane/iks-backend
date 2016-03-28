package com.iks.cms.core.parser;

import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public class ParseContext implements IParseContext {
  private String appObj;
  private IDataModel model;
  public IDataModel getModel() {
    return model;
  }
  public void setModel( IDataModel model ) {
    this.model = model;
  }
  @Override
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
}
