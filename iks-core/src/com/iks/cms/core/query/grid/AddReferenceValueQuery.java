package com.iks.cms.core.query.grid;

import com.iks.cms.core.appObj.App;
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
  private String field;
  // item id of current model
  private String parentItemId;
  // item id of inserted item
  private String itemId;

  public AddReferenceValueQuery(IDataModel model, String field, String parentItemId, String itemId) {
    this.model = model;
    this.field = field;
    this.parentItemId = parentItemId;
    this.itemId = itemId;
  }

  public void execute(SessionFactory sessionFactory) {
    AbstractChangeQuery insertQuery = buildSqlQuery();
    super.updateQuery(sessionFactory, insertQuery.toString());
  }

  private AbstractChangeQuery buildSqlQuery() {
    IDataField dataField = model.getField(field);
    if (dataField instanceof ManyToMany) {
      ManyToMany manyToMany = (ManyToMany) model.getField(field);
      Table table = new Table(manyToMany.getJoinTable());
      InsertQuery query = new InsertQuery(table);
      query.addUpdateColumn(table.getColumn(manyToMany.getTableField()), parentItemId);
      query.addUpdateColumn(table.getColumn(manyToMany.getInverseTableField()), itemId);
      return query;
    } else if (dataField instanceof OneToMany) {
      OneToMany oneToMany = (OneToMany) model.getField(field);
      IDataModel otherModel = App.getModel(oneToMany.getAppObj());
      Table table = new Table(otherModel.getTableName());
      UpdateQuery query = new UpdateQuery(table);
      query.addUpdateColumn(table.getColumn(oneToMany.getTableField()), parentItemId);
      query.setCriteriaById(Long.valueOf(itemId));
      return query;
    } else {
      throw new IllegalArgumentException("field must be oneToMany or manyToMany");
    }
  }
}
