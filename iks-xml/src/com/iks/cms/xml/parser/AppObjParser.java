package com.iks.cms.xml.parser;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.model.*;

import org.slf4j.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class AppObjParser {
  private static final Logger logger = LoggerFactory.getLogger( AppObjParser.class );
  private AppObj appObj;
  public IAppObj parse( String fileName ) throws Exception {
    Document doc = ParserUtils.parseFile( fileName );
    return parseRoot( doc.getDocumentElement() );
  }
  public IAppObj parse( Element root ) throws Exception {
    return parseRoot( root );
  }
  //appObj
  private IAppObj parseRoot( Element root ) throws Exception {
    appObj = new AppObj();
    appObj.setName( root.getAttribute( "name" ) );
    appObj.setLabel( root.getAttribute( "label" ) );
    NodeList fieldList = root.getChildNodes();
    appObj.setDataModel( parseDataModel( ( Element )root.getElementsByTagName( "data" ).item( 0 ) ) );
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element childElement = ( Element )node;
        switch( childElement.getTagName() ) {
          case "data":
            // we parse data before other elements
            break;
          case "list":
            appObj.setGridView( parseGridView( childElement ) );
            break;
          case "edit":
            appObj.setEditView( parseEditView( childElement ) );
            break;
          default:
            throw new Exception( "Wrong element " + childElement.getTagName() );
        }
      }
    }
    return appObj;
  }
  // data
  private IDataModel parseDataModel( Element modelElement ) throws Exception {
    ModelParser dataParser = new ModelParser();
    return dataParser.parse( modelElement.getAttribute( "url" ) );
  }
  //list
  private IGridView parseGridView( Element gridElement ) throws Exception {
    ListViewParser dataParser = new ListViewParser( appObj.getDataModel() );
    return dataParser.parse( gridElement.getAttribute( "url" ) );
  }
  //edit
  private EditView parseEditView( Element editElement ) throws Exception {
    EditViewParser dataParser = new EditViewParser( appObj.getDataModel() );
    return dataParser.parse( editElement.getAttribute( "url" ) );
  }
}
