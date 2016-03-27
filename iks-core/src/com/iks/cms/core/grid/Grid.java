package com.iks.cms.core.grid;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class Grid implements IGrid {
  private List< IGridColumn > fields = new ArrayList<>();
  public List< IGridColumn > getFields() {
    return fields;
  }
  public void setFields( List< IGridColumn > fields ) {
    this.fields = fields;
  }
  public void addField( IGridColumn field ) {
    this.fields.add( field );
  }
}
