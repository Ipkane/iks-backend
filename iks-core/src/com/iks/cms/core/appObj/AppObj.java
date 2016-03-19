package com.iks.cms.core.appObj;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public class AppObj implements IAppObj {
  private String name;
  private IDataModel dataModel;
  private IGrid      grid;
  public IDataModel getDataModel() {
    return dataModel;
  }
  public void setDataModel( IDataModel dataModel ) {
    this.dataModel = dataModel;
  }
  public IGrid getGrid() {
    return grid;
  }
  public void setGrid( IGrid grid ) {
    this.grid = grid;
  }
  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
}
