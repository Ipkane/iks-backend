package com.iks.cms.core.grid;

import com.fasterxml.jackson.annotation.*;
import com.iks.cms.common.json.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GridColumn extends GulElement implements IGridColumn {
  @JsonView( JsonViews.Normal.class )
  private String fieldName;
  @JsonView( JsonViews.Normal.class )
  private String label;
  @JsonView( JsonViews.Normal.class )
  private String displayField;
  public GridColumn() {
  }
  public GridColumn( IGridColumn src ) {
    fieldName = src.getFieldName();
    label = src.getLabel();
    displayField = src.getDisplayField();
  }
  @Override
  public String getFieldName() {
    return fieldName;
  }
  public void setFieldName( String fieldName ) {
    this.fieldName = fieldName;
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
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
    if( xmlElement.hasAttribute( GulConstant.ATTR_FIELD_NAME ) ) {
      setFieldName( xmlElement.getAttribute( GulConstant.ATTR_FIELD_NAME ) );
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
