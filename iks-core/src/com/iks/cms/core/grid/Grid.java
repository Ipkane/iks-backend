package com.iks.cms.core.grid;

import com.fasterxml.jackson.annotation.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.model.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class Grid implements IGrid {
  @JsonIgnore
  protected IDataModel dataModel;
  private List< IGridField > fields = new ArrayList<>();
  public List< IGridField > getFields() {
    return fields;
  }
  public void setFields( List< IGridField > fields ) {
    this.fields = fields;
  }
  public void addField( IGridField field ) {
    this.fields.add( field );
  }
  public IDataModel getDataModel() {
    return dataModel;
  }
  public void setDataModel( IDataModel dataModel ) {
    this.dataModel = dataModel;
  }
}
