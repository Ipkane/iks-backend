package com.iks.cms.core.grid;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.iks.cms.common.json.*;
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
  @JsonView( JsonViews.Internal.class )
  protected String       appObj;
  private   IFilterPanel filterPanel;
  @JsonView( JsonViews.Internal.class )
  private   List< IGridColumn > columns     = new ArrayList<>();
  @Override
  public List< IGridColumn > getColumns() {
    return columns;
  }
  public void setColumns( List< IGridColumn > columns ) {
    this.columns = columns;
  }
  public void addColumn( IGridColumn column ) {
    this.columns.add( column );
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
      column.applyModel( model.getField( column.getFieldName() ) );
      addColumn( column );
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
  public abstract String getTemplatePath();

  @Override
  public abstract String getTag();
  public IFilterPanel getFilterPanel() {
    return filterPanel;
  }
  public void setFilterPanel( IFilterPanel filterPanel ) {
    this.filterPanel = filterPanel;
  }
  public String toJson() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
    return objectMapper.writerWithView( JsonViews.Internal.class ).writeValueAsString( this ).replace( "\"", "\\\"" );
  }
  @Override
  public String getId() {
    return id;
  }
  @Override
  public void setId( String id ) {
    this.id = id;
  }
  @JsonView( JsonViews.Internal.class )
  public IEditView getEditView() {
    return App.getEditView(appObj);
  }
  @JsonView( JsonViews.Normal.class )
  public String getAppObjLabel() {
    return App.getAppObj(appObj).getLabel();
  }
}
