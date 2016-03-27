package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.hibernate.*;
import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class CreateModelQuery<T extends CreateModelQuery> extends CommonModelQuery<T> {
  private static final Logger logger = LoggerFactory.getLogger( CreateModelQuery.class );
  protected IDataItem item;
  public CreateModelQuery( IDataModel model, IDataItem item ) {
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
  protected InsertQuery buildSqlQuery() {
    InsertQuery sb = new InsertQuery();
    Table table = new Table( model.getTableName() );
    sb.setTable( table );
    for( String field : getFields() ) {
      IDataField dataField = model.getField( field );
      if( dataField.getName().equals( model.getPrimaryFieldName() ) ) {
        continue;
      }
      sb.addUpdateColumn( new Column( table, dataField.getTableField() ), item.getFieldValue( dataField.getName() ) );
    }
    return sb;
  }
}
