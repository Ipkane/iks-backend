package com.iks.cms.core.service;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.*;
import com.iks.cms.core.repository.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
@Service
public class AppObjService {
  private static final Logger                 logger    = LoggerFactory.getLogger( AppObjService.class );
  private              Map< String, IAppObj > appObjMap = new HashMap<>();
  @Autowired
  private CommonDao commonDao;
  public IGrid getGrid( String appObj ) {
    return appObjMap.get( appObj ).getGrid();
  }
  public IDataModel getModel( String appObj ) {
    return appObjMap.get( appObj ).getDataModel();
  }
  public List< IDataRow > getGridData( String appObj ) {
    GridQuery query = new GridQuery( getModel( appObj ), getGrid( appObj ) );
    return query.executeQuery( commonDao.getSessionFactory() );
  }
  public IDataRow getEditData( String appObj, Long itemId ) {
    SelectSingleItemQuery query = new SelectSingleItemQuery( getModel( appObj ), getEditView( appObj ), itemId );
    return query.executeQuery( commonDao.getSessionFactory() );
  }
  public void createNewItem( String appObj, IDataRow item ) {
    IEditView editView = getEditView( appObj );
    CreateItemQuery query = new CreateItemQuery( getModel( appObj ), editView, item );
    String sqlQuery = query.buildSqlQuery();
    logger.debug( sqlQuery );
    commonDao.updateQuery( sqlQuery );
  }
  public void updateItem( String appObj, IDataRow item ) {
    UpdateItemQuery query = new UpdateItemQuery( getModel( appObj ), getEditView( appObj ), item );
    query.executeQuery( commonDao.getSessionFactory() );
  }
  public void deleteItem( String appObj, Long itemId ) {
    DeleteItemQuery query = new DeleteItemQuery( getModel( appObj ), getEditView( appObj ), itemId );
    query.executeQuery( commonDao.getSessionFactory() );
  }
  public IEditView getEditView( String appObj ) {
    return getAppObj( appObj ).getEditView();
  }
  public void addAppObj( IAppObj appObj ) {
    appObjMap.put( appObj.getName(), appObj );
  }
  public IAppObj getAppObj( String appObj ) {
    return appObjMap.get( appObj );
  }
  public void clear() {
    appObjMap.clear();
  }
  public Collection< IAppObj > getAppObjList() {
    return appObjMap.values();
  }
}
