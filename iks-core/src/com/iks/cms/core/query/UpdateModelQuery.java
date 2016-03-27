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
public class UpdateModelQuery<T extends UpdateModelQuery> extends CommonModelQuery<T> {
  private static final Logger logger = LoggerFactory.getLogger( UpdateEditViewQuery.class );
  protected IDataItem item;
  public UpdateModelQuery( IDataModel model, IDataItem item ) {
    super( model );
    setItem( item );
  }
  public void executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery().toString();
    logger.debug( sqlQuery );
    updateQuery( sessionFactory, sqlQuery );
  }
  protected UpdateQuery buildSqlQuery() {
    UpdateQuery sb = new UpdateQuery();
    Table table = new Table( model.getTableName() );
    sb.setTable( table );
    for( String field : getFields() ) {
      if( field.equals( model.getPrimaryFieldName() ) ) {
        continue;
      }
      IDataField dataField = model.getField( field );
      sb.addUpdateColumn( new Column( table, dataField.getTableField() ), item.getFieldValue( dataField.getName() ) );
    }
    Column idColumn = new Column( table, model.getPrimaryFieldName() );
    sb.addCriteria( new MatchCriteria( idColumn, getItemId(), MatchType.Eq ) );
    return sb;
  }
  public IDataItem getItem() {
    return item;
  }
  public void setItem( IDataItem item ) {
    this.item = item;
  }
  public Long getItemId() {
    return Long.valueOf( item.getFieldValue( model.getPrimaryFieldName() ).toString() );
  }
}
