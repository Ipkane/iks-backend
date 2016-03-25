package com.iks.cms.xml.parser;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.panel.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.slf4j.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GridViewParser {
  private static final Logger logger = LoggerFactory.getLogger( GridViewParser.class );
  private IDataModel model;
  public GridViewParser( IDataModel model ) {
    this.model = model;
  }
  public IGridView parse( String fileName ) throws Exception {
    Document doc = ParserUtils.parseFile( fileName );
    return parseRoot( doc );
  }
  private IGridView parseRoot( Document doc ) throws Exception {
    Element root = doc.getDocumentElement();
    NodeList nodeList = root.getChildNodes();
    GridView gridView = new GridView();
    for( int i = 0; i < nodeList.getLength(); i++ ) {
      Node node = nodeList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element element = ( Element )node;
        switch( element.getTagName() ) {
          case ListConstant.TABLE:
            Grid grid = parseTable( element );
            gridView.setGrid( grid );
            break;
          case ListConstant.FILTER:
            IGulFilterPanel filterPanel = parseFilterPanel( element );
            gridView.setFilterPanel( filterPanel );
            break;
          default:
            throw new Exception( "Couldn't parse element " + element.getTagName() );
        }
      }
    }
    return gridView;
  }
  private Grid parseTable( Element tableElement ) {
    Grid grid = new Grid();
    NodeList fieldList = tableElement.getElementsByTagName( ListConstant.FIELD );
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element fieldElement = ( Element )node;
        grid.addField( parseField( fieldElement ) );
      }
    }
    return grid;
  }
  private IGulFilterPanel parseFilterPanel( Element filterElement ) throws Exception {
    GulFilterPanel filterPanel = new GulFilterPanel(model);
    filterPanel.parse(filterElement );
    return filterPanel;
  }
  protected IDataModel getModel() {
    return model;
  }
  // field
  private IGridField parseField( Element fieldElement ) {
    GridField field = new GridField();
    field.setName( fieldElement.getAttribute( "name" ) );
    DataField dataField = ( DataField )model.getField( field.getName() );
    if( fieldElement.hasAttribute( "label" ) ) {
      field.setLabel( fieldElement.getAttribute( "label" ) );
    } else {
      field.setLabel( dataField.getLabel() );
    }
    return field;
  }
}
