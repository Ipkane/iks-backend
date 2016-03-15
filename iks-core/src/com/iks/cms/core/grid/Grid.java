package com.iks.cms.core.grid;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class Grid implements IGrid {
  private List< IGridField > fields;
  public List< IGridField > getFields() {
    return fields;
  }
  public void setFields( List< IGridField > fields ) {
    this.fields = fields;
  }
  public void addField( IGridField field ) {
    this.fields.add( field );
  }
}
