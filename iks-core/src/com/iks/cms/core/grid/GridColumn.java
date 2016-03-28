package com.iks.cms.core.grid;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.element.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GridColumn extends GulElement implements IGridColumn {
  private String name;
  private String label;
  private String displayField;
  public GridColumn() {
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
  @Override
  public String getTag() {
    return null;
  }
  @Override
  public void parse( Element xmlElement ) throws Exception {
    if( xmlElement.hasAttribute( GulConstant.ATTR_NAME ) ) {
      setName( xmlElement.getAttribute( GulConstant.ATTR_NAME ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_LABEL ) ) {
      setLabel( xmlElement.getAttribute( GulConstant.ATTR_LABEL ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_DISPLAY_FIELD ) ) {
      setDisplayField( xmlElement.getAttribute( GulConstant.ATTR_DISPLAY_FIELD ) );
    }
  }
  public void applyModel( IDataField dataField ) {
    if( getLabel() == null ) {
      setLabel( dataField.getLabel() );
    }
  }
}
