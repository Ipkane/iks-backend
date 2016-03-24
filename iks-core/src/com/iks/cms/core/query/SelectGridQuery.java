package com.iks.cms.core.query;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class SelectGridQuery extends SelectModelQuery {
  private static final Logger logger = LoggerFactory.getLogger( SelectGridQuery.class );
  private IGrid grid;
  public SelectGridQuery( IDataModel model, IGrid grid ) {
    super( model );
    this.grid = grid;
    for( IGridField field : grid.getFields() ) {
      addField( field.getName() );
    }
  }
  public IGrid getGrid() {
    return grid;
  }
  public void setGrid( IGrid grid ) {
    this.grid = grid;
  }
//  public List< IDataItem > executeQuery( SessionFactory sessionFactory ) {
  //    String sqlQuery = buildSqlQuery();
  //    logger.debug( sqlQuery );
  //    List rows = selectQuery( sessionFactory, sqlQuery );
  //    List< IDataItem > resultList = new ArrayList<>();
  //    for( Object rowData : rows ) {
  //      DataItem resultItem = new DataItem();
  //      Object[] data = ( Object[] )rowData;
  //      int i = 0;
  //      for( IGridField field : grid.getFields() ) {
  //        resultItem.addFieldValue( field.getName(), data[i] );
  //        i++;
  //      }
  //      resultList.add( resultItem );
  //    }
  //    return resultList;
  //  }
  }
