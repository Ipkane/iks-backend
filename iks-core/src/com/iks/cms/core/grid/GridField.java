package com.iks.cms.core.grid;

/**
 * @author Igor Kaynov
 */
public class GridField implements IGridField {
  private String name;
  private String label;
  public GridField() {
  }
  public GridField( String name, String label ) {
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
}
