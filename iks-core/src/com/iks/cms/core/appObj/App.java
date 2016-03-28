package com.iks.cms.core.appObj;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.service.*;

import java.util.*;

/**
 * @author Igor Kaynov Main class singleton. Contains all app objs and models
 */
public class App {
  private static App instance;
  private Map< String, IAppObj > appObjMap = new LinkedHashMap<>();
  private Map< String, IGrid >   grids     = new HashMap<>();
  private AppObjService service;
  private App() {
  }
  public static App getInstance() {
    if( instance == null ) {
      instance = new App();
    }
    return instance;
  }
  public static void addGrid( String gridId, IGrid grid ) {
    if (getInstance().grids.containsKey( gridId )) {
      throw new RuntimeException( "Duplicate grid id " + gridId );
    }
    getInstance().grids.put( gridId, grid );
  }
  public static IGrid getGrid( String gridId ) {
    return getInstance().grids.get( gridId );
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
  public static IListView getListView( String appObj ) {
    return getAppObj( appObj ).getListView();
  }
  public static void clearAppObjs() {
    getInstance().appObjMap.clear();
    getInstance().grids.clear();
  }
  public static Collection< IAppObj > getAppObjList() {
    return getInstance().appObjMap.values();
  }
  public Map< String, List< SelectOption > > getOptionsMap( String appObj, List< IGulInputField > fields ) {
    return service.getOptionsMap( appObj, fields );
  }
  public AppObjService getService() {
    return service;
  }
  public void setService( AppObjService service ) {
    this.service = service;
  }
}
