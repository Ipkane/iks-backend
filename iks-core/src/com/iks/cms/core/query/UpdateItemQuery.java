package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.hibernate.*;
import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class UpdateItemQuery extends CommonDaoQuery {
  private static final Logger logger = LoggerFactory.getLogger( SelectSingleItemQuery.class );
  private IEditView  editView;
  private IDataModel model;
  private IDataItem  item;
  private Long       itemId;
  public UpdateItemQuery( IDataModel model, IEditView editView, IDataItem item ) {
    this.model = model;
    this.editView = editView;
    setItem( item );
  }
  public void executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery();
    logger.debug( sqlQuery );
    updateQuery( sessionFactory, sqlQuery );
  }
  private String buildSqlQuery() {
    if( itemId == null ) {
      throw new IllegalArgumentException( "Item id is null" );
    }
    UpdateQuery sb = new UpdateQuery();
    Table table = new Table( model.getTableName() );
    sb.setTable( table );
    for( IGulInput field : editView.getFields() ) {
      IDataField dataField = model.getField( field.getName() );
      if( dataField.getName().equals( "id" ) ) {
        continue;
      }
      sb.addUpdateColumn( new Column( table, dataField.getTableField() ), item.getFieldValue( dataField.getName() ) );
    }
    Column idColumn = new Column( table, "id" );
    sb.addCriteria( new MatchCriteria( idColumn, itemId, MatchType.Eq ) );
    return sb.toString();
  }
  public IDataItem getItem() {
    return item;
  }
  public void setItem( IDataItem item ) {
    this.item = item;
    itemId = Long.valueOf( item.getFieldValue( "id" ).toString() );
  }
}
