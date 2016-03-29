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
public class AddReferenceValueQuery extends CommonDaoQuery {
  private IDataModel model;
  private String     field;
  // item id of current model
  private String     parentItemId;
  // item id of inserted item
  private String     itemId;
  public AddReferenceValueQuery( IDataModel model, String field, String parentItemId, String itemId ) {
    this.model = model;
    this.field = field;
    this.parentItemId = parentItemId;
    this.itemId = itemId;
  }
  public void execute( SessionFactory sessionFactory ) {
    InsertQuery insertQuery = buildSqlQuery();
    super.updateQuery( sessionFactory, insertQuery.toString() );
  }
  private InsertQuery buildSqlQuery() {
    InsertQuery query = new InsertQuery();
    ManyToMany dataField = ( ManyToMany )model.getField( field );
    Table table = new Table( dataField.getJoinTable() );
    query.setTable( table );
    query.addUpdateColumn( table.getColumn( dataField.getTableField() ), parentItemId );
    query.addUpdateColumn( table.getColumn( dataField.getInverseTableField() ), itemId );
    return query;
  }
}
