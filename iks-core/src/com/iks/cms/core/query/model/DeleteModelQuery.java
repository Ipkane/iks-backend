package com.iks.cms.core.query.model;

import com.iks.cms.core.model.*;
import com.iks.cms.core.query.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.hibernate.*;
import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class DeleteModelQuery extends CommonModelQuery< DeleteModelQuery > {
  private static final Logger logger = LoggerFactory.getLogger( DeleteModelQuery.class );
  protected Long itemId;
  public DeleteModelQuery( IDataModel model, Long itemId ) {
    super( model );
    if( itemId == null ) {
      throw new IllegalArgumentException( "Item id is null" );
    }
    this.itemId = itemId;
  }
  public void executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery().toString();
    logger.debug( sqlQuery );
    updateQuery( sessionFactory, sqlQuery );
  }
  private DeleteQuery buildSqlQuery() {
    if( itemId == null ) {
      throw new IllegalArgumentException( "Item id is null" );
    }
    DeleteQuery sb = new DeleteQuery();
    Table table = new Table( model.getTableName() );
    sb.setTable( table );
    Column idColumn = new Column( table, model.getPrimaryFieldName() );
    sb.addCriteria( new MatchCriteria( idColumn, itemId, MatchType.Eq ) );
    return sb;
  }
}
