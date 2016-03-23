package com.iks.cms.core.query;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.hibernate.*;
import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class SelectSingleItemQuery extends CommonDaoQuery {
  private static final Logger logger = LoggerFactory.getLogger( SelectSingleItemQuery.class );
  private IEditView  editView;
  private IDataModel model;
  private Long       itemId;
  public SelectSingleItemQuery( IDataModel model, IEditView editView, Long itemId ) {
    this.model = model;
    this.editView = editView;
    this.itemId = itemId;
  }
  public DataItem executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery();
    logger.debug( sqlQuery );
    Object[] row = ( Object[] )selectSingleQuery( sessionFactory, sqlQuery );
    DataItem resultItem = new DataItem();
    int i = 0;
    for( IGulInputField field : editView.getFields() ) {
      resultItem.addFieldValue( field.getName(), row[i] == null ? null : row[i].toString() );
      i++;
    }
    return resultItem;
  }
  private String buildSqlQuery() {
    SelectQuery sb = new SelectQuery();
    Table table = new Table( model.getTableName(), "t" );
    sb.from( table );
    for( IGulInputField field : editView.getFields() ) {
      IDataField dataField = model.getField( field.getName() );
      sb.addColumn( new Column( table, dataField.getTableField(), dataField.getName() ) );
    }
    Column idColumn = new Column( table, "id" );
    sb.addCriteria( new MatchCriteria( idColumn, itemId, MatchType.Eq ) );
    return sb.toString();
  }
  public Long getItemId() {
    return itemId;
  }
  public void setItemId( Long itemId ) {
    this.itemId = itemId;
  }
}
