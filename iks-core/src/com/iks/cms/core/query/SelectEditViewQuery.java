package com.iks.cms.core.query;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;

import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class SelectEditViewQuery extends SelectModelQuery {
  private static final Logger logger = LoggerFactory.getLogger( SelectEditViewQuery.class );
  private IEditView editView;
  public SelectEditViewQuery( IDataModel model, IEditView editView, Long itemId ) {
    super( model );
    this.editView = editView;
    for( IGulInputField field : editView.getFields() ) {
      addField( field.getName() );
    }
    addFilter( model.getPrimaryFieldName(), itemId );
  }
//  public DataItem executeSingleQuery( SessionFactory sessionFactory ) {
//    String sqlQuery = buildSqlQuery();
//    logger.debug( sqlQuery );
//    Object[] row = ( Object[] )selectSingleQuery( sessionFactory, sqlQuery );
//    DataItem resultItem = new DataItem();
//    int i = 0;
//    for( IGulInputField field : editView.getFields() ) {
//      resultItem.addFieldValue( field.getName(), row[i] == null ? null : row[i].toString() );
//      i++;
//    }
//    return resultItem;
//  }
}
