package com.iks.cms.core.query.model;

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
public abstract class AbstractChangeModelQuery< T extends AbstractChangeModelQuery > extends CommonModelQuery< T > {
  private static final Logger logger = LoggerFactory.getLogger( CreateModelQuery.class );
  protected IDataItem item;
  public AbstractChangeModelQuery( IDataModel model, IDataItem item ) {
    super( model );
    setItem( item );
  }
  public IDataItem getItem() {
    return item;
  }
  public void setItem( IDataItem item ) {
    this.item = item;
  }
  public void executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery().toString();
    logger.debug( sqlQuery );
    updateQuery( sessionFactory, sqlQuery );
  }
  protected abstract AbstractChangeQuery buildSqlQuery();
  protected void fillQuery(AbstractChangeQuery query) {
    Table table = new Table( model.getTableName() );
    query.setTable( table );
    for( String field : getFields() ) {
      if( field.equals( model.getPrimaryFieldName() ) ) {
        continue;
      }
      IDataField dataField = model.getField( field );
      if( dataField instanceof ManyToOne ) {
        ManyToOne manyToOne = ( ManyToOne )dataField;
        Map< String, Object > joinedItem = ( Map< String, Object > )item.getFieldValue( dataField.getFieldName() );
        if (joinedItem != null) {
          query.addUpdateColumn( new Column( table, dataField.getTableField() ), joinedItem.get( manyToOne.getReferenceField() ) );
        }
      } else {
        query.addUpdateColumn( new Column( table, dataField.getTableField() ), item.getFieldValue( dataField.getFieldName() ) );
      }
    }
  }
}
