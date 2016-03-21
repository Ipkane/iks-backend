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
public class CreateItemQuery extends CommonDaoQuery {
  private static final Logger logger = LoggerFactory.getLogger( SelectSingleItemQuery.class );
  private IEditView  editView;
  private IDataModel model;
  private IDataRow   item;
  public CreateItemQuery( IDataModel model, IEditView editView, IDataRow item ) {
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
    InsertQuery sb = new InsertQuery();
    Table table = new Table( model.getTableName() );
    sb.setTable( table );
    for( IGulInput field : editView.getFields() ) {
      IDataField dataField = model.getField( field.getName() );
      if( dataField.getName().equals( "id" ) ) {
        continue;
      }
      sb.addUpdateColumn( new Column( table, dataField.getTableField() ), item.getFieldValue( dataField.getName() ) );
    }
    return sb.toString();
  }
  public IDataRow getItem() {
    return item;
  }
  public void setItem( IDataRow item ) {
    this.item = item;
  }
}
