package com.iks.cms.core.query.model;

import com.iks.cms.core.model.*;
import com.iks.cms.core.query.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class CommonModelQuery< T extends CommonModelQuery > extends CommonDaoQuery {
  protected IDataModel model;
  private List< String > fields = new ArrayList<>();
  public CommonModelQuery( IDataModel model ) {
    this.model = model;
  }
  public List< String > getFields() {
    if( fields.size() != 0 ) {
      return fields;
    }
    return model.getFieldNames();
  }
  public T setFields( List< String > fieldsList ) {
    this.fields.clear();
    for (String field: fieldsList) {
      addField(field);
    }
    return ( T )this;
  }
  public T addField( String field ) {
    if( field == null || fields.contains( field ) ) {
      return ( T )this;
    }
    this.fields.add( field );
    return ( T )this;
  }
  public IDataModel getModel() {
    return model;
  }
  public void setModel( IDataModel model ) {
    this.model = model;
  }
}
