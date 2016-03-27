package com.iks.cms.core.gul.container;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

import java.util.*;
import java.util.stream.*;

/**
 * @author Igor Kaynov
 */
public abstract class GulFieldContainer extends GulContainer implements IFieldContainer {
  protected IDataModel model;
  public GulFieldContainer( IDataModel model ) {
    super();
    this.model = model;
  }
  protected List< IGulInputField > fields = new ArrayList<>();
  @Override
  public List< IGulInputField > getFields() {
    return fields;
  }
  public void setFields( List< IGulInputField > fields ) {
    this.fields.clear();
    for( IGulInputField field : fields ) {
      addField( field );
    }
  }
  public void addField( IGulInputField field ) {
    fields.add( field );
  }
  public void removeField( IGulInputField field ) {
    fields.remove( field );
  }
  @Override
  public void parse( Element xmlElement ) throws Exception {
    super.parse( xmlElement );
    fields = fields.stream().filter( field -> StringUtils.isNotBlank(field.getName() )).collect( Collectors.toList() );
    for (IGulInputField field: fields) {
      if (field.getName() != null) {
        IDataField dataField = model.getField( field.getName() );
        if (dataField != null) {
          field.applyModel( model.getField( field.getName() ) );
        }
      }
    }
  }
}
