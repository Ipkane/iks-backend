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
  private List< IDataField > fields = new ArrayList<>();
  public CommonModelQuery( IDataModel model ) {
    this.model = model;
  }
  public List< IDataField > getFields() {
    return fields.size() == 0 ? model.getFields() : fields;
  }
  public T setFields( List< String > fieldsList ) {
    this.fields.clear();
    this.fields.addAll( fieldsList.stream().map( model::getField ).collect( Collectors.toList() ) );
    return ( T )this;
  }
  public T addField( String field ) {
    this.fields.add( model.getField( field ) );
    return ( T )this;
  }
}
