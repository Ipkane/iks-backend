package com.iks.cms.core.appObj;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class App {
  private static App instance;
  private Map< String, IAppObj > appObjMap = new LinkedHashMap<>();
  private App() {
  }
  private static App getInstance() {
    if( instance == null ) {
      instance = new App();
    }
    return instance;
  }
  public static IGrid getGrid( String appObj ) {
    return getAppObj( appObj ).getGrid();
  }
  public static IDataModel getModel( String appObj ) {
    return getAppObj( appObj ).getDataModel();
  }
  public static void addAppObj( IAppObj appObj ) {
    getInstance().appObjMap.put( appObj.getName(), appObj );
  }
  public static IAppObj getAppObj( String appObj ) {
    return getInstance().appObjMap.get( appObj );
  }
  public static IEditView getEditView( String appObj ) {
    return getAppObj( appObj ).getEditView();
  }
  public static void clearAppObjs() {
    getInstance().appObjMap.clear();
  }
  public static Collection< IAppObj > getAppObjList() {
    return getInstance().appObjMap.values();
  }
}
