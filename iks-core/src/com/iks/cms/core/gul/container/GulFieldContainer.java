package com.iks.cms.core.gul.container;

import com.iks.cms.core.gul.form.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class GulFieldContainer extends GulContainer implements IFieldContainer {
  protected List< IGulInputField > fields = new ArrayList<>();
  @Override
  public List< IGulInputField > getFields() {
    return fields;
  }
  public void setFields( List< IGulInputField > fields ) {
    this.fields = fields;
  }
  public void addField( IGulInputField field ) {
    fields.add( field );
  }
  public void removeField(IGulInputField field) {
    fields.remove( field );
  }
}
