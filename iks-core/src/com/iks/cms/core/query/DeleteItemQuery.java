package com.iks.cms.core.query;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.hibernate.*;
import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class DeleteItemQuery extends CommonDaoQuery {
  private static final Logger logger = LoggerFactory.getLogger( SelectEditViewQuery.class );
  private IEditView  editView;
  private IDataModel model;
  private Long       itemId;
  public DeleteItemQuery( IDataModel model, IEditView editView, Long itemId ) {
    this.model = model;
    this.editView = editView;
    this.itemId = itemId;
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
    DeleteQuery sb = new DeleteQuery();
    Table table = new Table( model.getTableName() );
    sb.setTable( table );
    Column idColumn = new Column( table, "id" );
    sb.addCriteria( new MatchCriteria( idColumn, itemId, MatchType.Eq ) );
    return sb.toString();
  }
}
