package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.sql.*;
import com.iks.cms.core.sql.query.*;

import org.hibernate.*;
import org.slf4j.*;

import java.util.*;
import java.util.stream.*;

/**
 * @author Igor Kaynov
 */
public class ModelQuery extends CommonDaoQuery {
  private static final Logger logger = LoggerFactory.getLogger( ModelQuery.class );
  private IDataModel model;
  private List< IDataField > fields = new ArrayList<>();
  public ModelQuery( IDataModel model ) {
    this.model = model;
  }
  public List< IDataItem > executeQuery( SessionFactory sessionFactory ) {
    String sqlQuery = buildSqlQuery();
    logger.debug( sqlQuery );
    List rows = selectQuery( sessionFactory, sqlQuery );
    List< IDataItem > resultList = new ArrayList<>();
    for( Object rowData : rows ) {
      DataItem resultItem = new DataItem();
      Object[] data = ( Object[] )rowData;
      int i = 0;
      for( IDataField field : getFields() ) {
        resultItem.addFieldValue( field.getName(), data[i] );
        i++;
      }
      resultList.add( resultItem );
    }
    return resultList;
  }
  private String buildSqlQuery() {
    SelectQuery sb = new SelectQuery();
    Table table = new Table( model.getTableName(), "t" );
    sb.from( table );
    for( IDataField field : getFields() ) {
      sb.addColumn( new Column( table, field.getTableField(), field.getName() ) );
    }
    return sb.toString();
  }
  public List< IDataField > getFields() {
    return fields.size() == 0 ? model.getFields() : fields;
  }
  public ModelQuery setFields( List< String > fieldsList ) {
    this.fields.clear();
    this.fields.addAll( fieldsList.stream().map( model::getField ).collect( Collectors.toList() ) );
    return this;
  }
}
