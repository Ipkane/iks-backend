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
    IGrid grid = getGrid( appObj );
    GridQuery query = new GridQuery( getModel( appObj ), grid );
    String sqlQuery = query.buildSqlQuery();
    logger.debug( sqlQuery );
    List rows = commonDao.selectQuery( sqlQuery );
    List< IDataRow > resultList = new ArrayList<>();
    for( Object rowData : rows ) {
      DataRow resultItem = new DataRow();
      Object[] data = ( Object[] )rowData;
      int i = 0;
      for( IGridField field : grid.getFields() ) {
        resultItem.addFieldValue( field.getName(), data[i] );
        i++;
      }
      resultList.add( resultItem );
    }
    return resultList;
  }
  public IDataRow getEditData( String appObj, Long itemId ) {
    IEditView editView = getEditView( appObj );
    EditViewQuery query = new EditViewQuery( getModel( appObj ), editView );
    query.setItemId( itemId );
    String sqlQuery = query.buildSqlQuery();
    logger.debug( sqlQuery );
    Object[] row = ( Object[] )commonDao.selectSingleQuery( sqlQuery );
    DataRow resultItem = new DataRow();
    int i = 0;
    for( IGulInput field : editView.getFields() ) {
      resultItem.addFieldValue( field.getName(), row[i] );
      i++;
    }
    return resultItem;
  }
  public void createNewItem( String appObj, IDataRow item ) {
    IEditView editView = getEditView( appObj );
    CreateItemQuery query = new CreateItemQuery( getModel( appObj ), editView, item );
    String sqlQuery = query.buildSqlQuery();
    logger.debug( sqlQuery );
    commonDao.updateQuery( sqlQuery );
  }
  public void updateItem( String appObj, IDataRow item ) {
    IEditView editView = getEditView( appObj );
    UpdateItemQuery query = new UpdateItemQuery( getModel( appObj ), editView, item );
    String sqlQuery = query.buildSqlQuery();
    logger.debug( sqlQuery );
    commonDao.updateQuery( sqlQuery );
  }
  public void deleteItem( String appObj, Long itemId ) {
    IEditView editView = getEditView( appObj );
    DeleteItemQuery query = new DeleteItemQuery( getModel( appObj ), editView, itemId );
    String sqlQuery = query.buildSqlQuery();
    logger.debug( sqlQuery );
    commonDao.updateQuery( sqlQuery );
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
