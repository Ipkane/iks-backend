package com.iks.cms.core.grid;

/**
 * @author Igor Kaynov
 */
public class GridColumn implements IGridColumn {
  private String name;
  private String label;
  private String displayField;
  public GridColumn() {
  }
  public GridColumn( String name, String label ) {
    this.name = name;
    this.label = label;
  }
  @Override
  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
  @Override
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
  @Override
  public String getDisplayField() {
    return displayField;
  }
  public void setDisplayField( String displayField ) {
    this.displayField = displayField;
  }
}
