package com.iks.cms.web.service;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.query.*;
import com.iks.cms.web.grids.*;
import com.iks.cms.web.repository.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import javax.annotation.*;

/**
 * @author Igor Kaynov
 */
@Service
public class GridService {
  private static final Logger               logger = LoggerFactory.getLogger( GridService.class );
  private              Map< String, IGrid > grids  = new HashMap<>();
  @Autowired
  private CommonDao commonDao;
  @PostConstruct
  public void init() {
    grids.put( "employee", new EmployeeGrid() );
  }
  public IGrid getGrid( String name ) {
    return grids.get( name );
  }
  public List< IGridDataRow > getGridData( String name ) {
    IGrid grid = getGrid( name );
    GridQuery query = new GridQuery( grid );
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
}
