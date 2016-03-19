package com.iks.cms.core.service;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.*;
import com.iks.cms.core.repository.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import javax.annotation.*;

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
  public List< IGridDataRow > getGridData( String appObj ) {
    IGrid grid = getGrid( appObj );
    GridQuery query = new GridQuery( getModel( appObj ), grid );
    String sqlQuery = query.buildSqlQuery();
    logger.debug( sqlQuery );
    List rows = commonDao.selectQuery( sqlQuery );
    List< IGridDataRow > resultList = new ArrayList<>();
    for( Object rowData : rows ) {
      GridDataRow resultItem = new GridDataRow();
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
  public void addAppObj(IAppObj appObj) {
    appObjMap.put( appObj.getName(), appObj );
  }
}
