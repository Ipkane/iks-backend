package com.iks.cms.core.query;

import com.iks.cms.core.data.*;
import com.iks.cms.core.model.*;

import java.util.*;
import java.util.stream.*;

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
    this.fields.addAll( fieldsList );
    return ( T )this;
  }
  public T addField( String field ) {
    if( field == null || fields.contains( field ) ) {
      return ( T )this;
    }
    this.fields.add( field );
    return ( T )this;
  }
}
