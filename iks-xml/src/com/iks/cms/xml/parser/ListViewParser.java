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
public class ListViewParser {
  private static final Logger logger = LoggerFactory.getLogger( ListViewParser.class );
  private IDataModel model;
  public ListViewParser( IDataModel model ) {
    this.model = model;
  }
  public IListView parse( String fileName ) throws Exception {
    Document doc = ParserUtils.parseFile( fileName );
    return parseRoot( doc );
  }
  private IListView parseRoot( Document doc ) throws Exception {
    Element root = doc.getDocumentElement();
    NodeList nodeList = root.getChildNodes();
    ListView gridView = new ListView();
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
    NodeList fieldList = tableElement.getElementsByTagName( ListConstant.COLUMN );
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
    GulFilterPanel filterPanel = new GulFilterPanel( model );
    filterPanel.parse( filterElement );
    return filterPanel;
  }
  protected IDataModel getModel() {
    return model;
  }
  // field
  private IGridColumn parseField( Element fieldElement ) {
    GridColumn field = new GridColumn();
    field.setName( fieldElement.getAttribute( ListConstant.NAME_ATTR ) );
    AbstractDataField dataField = ( AbstractDataField )model.getField( field.getName() );
    if( fieldElement.hasAttribute( ListConstant.LABEL_ATTR ) ) {
      field.setLabel( fieldElement.getAttribute( ListConstant.LABEL_ATTR ) );
    } else {
      field.setLabel( dataField.getLabel() );
    }
    if( fieldElement.hasAttribute( ListConstant.DISPLAY_FIELD_ATTR ) ) {
      field.setDisplayField( fieldElement.getAttribute( ListConstant.DISPLAY_FIELD_ATTR ) );
    }
    return field;
  }
}
