package com.iks.cms.core.grid;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.iks.cms.core.appObj.*;
import com.iks.cms.core.constant.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.panel.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class BaseGrid extends GulElement implements IBaseGrid {
  protected String       appObj;
  private   IFilterPanel filterPanel;
  protected Boolean showToolbar = true;
  protected String parentAppObj;
  private List< IGridColumn > fields = new ArrayList<>();
  @Override
  public List< IGridColumn > getFields() {
    return fields;
  }
  public void setFields( List< IGridColumn > fields ) {
    this.fields = fields;
  }
  public void addField( IGridColumn field ) {
    this.fields.add( field );
  }
  @Override
  public String getAppObj() {
    return appObj;
  }
  public void setAppObj( String appObj ) {
    this.appObj = appObj;
  }
  @Override
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
    if( xmlElement.hasAttribute( ListConstant.ATTR_APP_OBJ ) ) {
      setAppObj( xmlElement.getAttribute( ListConstant.ATTR_APP_OBJ ) );
    }
    App.addGrid( id, this );
    IDataModel model = App.getModel( appObj );
    NodeList elements = xmlElement.getElementsByTagName( ListConstant.COLUMN );
    for( int i = 0; i < elements.getLength(); i++ ) {
      Element element = ( Element )elements.item( i );
      GridColumn column = ( GridColumn )AppFactory.createElement( element.getTagName() );
      column.parse( context, element );
      column.applyModel( model.getField( column.getName() ) );
      addField( column );
    }
    elements = xmlElement.getElementsByTagName( ListConstant.FILTER_PANEL );
    if( elements.getLength() > 0 ) {
      Element element = ( Element )elements.item( 0 );
      FilterPanel filterPanel = ( FilterPanel )AppFactory.createElement( element.getTagName() );
      filterPanel.setAppObj( appObj );
      filterPanel.parse( context, element );
      setFilterPanel( filterPanel );
    }
  }
  @Override
  public String getTemplatePath() {
    return "app/appTable";
  }
  @Override
  public String getTag() {
    return ListConstant.APP_TABLE;
  }
  public IFilterPanel getFilterPanel() {
    return filterPanel;
  }
  public void setFilterPanel( IFilterPanel filterPanel ) {
    this.filterPanel = filterPanel;
  }
  public String toJson() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString( new GridJson() ).replace( "\"", "\\\"" );
  }
  @Override
  public String getId() {
    return id;
  }
  @Override
  public void setId( String id ) {
    this.id = id;
  }
  public Boolean getShowToolbar() {
    return showToolbar;
  }
  public void setShowToolbar( Boolean showToolbar ) {
    this.showToolbar = showToolbar;
  }
  private class GridJson {
    public List< IGridColumn > getFields() {
      return BaseGrid.this.getFields();
    }
    public String getId() {
      return BaseGrid.this.id;
    }
    public String getAppObj() {
      return BaseGrid.this.appObj;
    }
  }
  public String getParentAppObj() {
    return parentAppObj;
  }
  public void setParentAppObj( String parentAppObj ) {
    this.parentAppObj = parentAppObj;
  }
}
