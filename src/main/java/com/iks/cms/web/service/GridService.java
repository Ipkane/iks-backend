package com.iks.cms.web.service;

import com.iks.cms.core.grid.*;
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
  private EmployeeDao employeeDao;
  @PostConstruct
  public void init() {
    grids.put( "employee", new EmployeeGrid() );
  }
  public IGrid getGrid( String name ) {
    return grids.get( name );
  }
  public List< IGridDataRow > getGridData( String name ) {
    List rows = employeeDao.getEmployees();
    for( Object rowData : rows ) {
      Object[] data = (Object[])rowData;
      for (Object value: data)
      logger.debug( value.toString() );
    }
    EmployeeGrid grid = ( EmployeeGrid )grids.get( name );
    return grid.getTestData();
  }
}
