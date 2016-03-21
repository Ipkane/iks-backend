package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.hibernate.*;
import org.slf4j.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GridQuery extends CommonDaoQuery {
  private static final Logger logger = LoggerFactory.getLogger( GridQuery.class );
  private IGrid      grid;
  private IDataModel model;
  public GridQuery( IDataModel model, IGrid grid ) {
    this.model = model;
    this.grid = grid;
  }
  public IGrid getGrid() {
    return grid;
  }
  public void setGrid( IGrid grid ) {
    this.grid = grid;
  }
  public List< IDataRow > executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery();
    logger.debug( sqlQuery );
    List rows = selectQuery( sessionFactory, sqlQuery );
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
  private String buildSqlQuery() {
    SelectQuery sb = new SelectQuery();
    Table table = new Table( model.getTableName(), "t" );
    sb.from( table );
    for( IGridField field : grid.getFields() ) {
      IDataField dataField = model.getField( field.getName() );
      sb.addColumn( new Column( table, dataField.getTableField(), dataField.getName() ) );
    }
    return sb.toString();
  }
}
