package com.iks.cms.core.query.grid;

import com.iks.cms.core.data.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.hibernate.*;

/**
 * @author Igor Kaynov Adds value to many to many table
 */
public class DeleteManyToManyQuery extends CommonDaoQuery {
  private IDataModel model;
  private String     field;
  // item id of current model
  private String     parentItemId;
  // item id of joined item
  private String     itemId;
  public DeleteManyToManyQuery( IDataModel model, String field, String parentItemId, String itemId ) {
    this.model = model;
    this.field = field;
    this.parentItemId = parentItemId;
    this.itemId = itemId;
  }
  public void execute( SessionFactory sessionFactory ) {
    DeleteQuery deleteQuery = buildSqlQuery();
    super.updateQuery( sessionFactory, deleteQuery.toString() );
  }
  private DeleteQuery buildSqlQuery() {
    DeleteQuery query = new DeleteQuery();
    ManyToMany dataField = ( ManyToMany )model.getField( field );
    Table table = new Table( dataField.getJoinTable() );
    query.setTable( table );
    query.addCriteria( new MatchCriteria( table.getColumn( dataField.getTableField() ), parentItemId ) );
    query.addCriteria( new MatchCriteria( table.getColumn( dataField.getInverseTableField() ), itemId ) );
    return query;
  }
}
