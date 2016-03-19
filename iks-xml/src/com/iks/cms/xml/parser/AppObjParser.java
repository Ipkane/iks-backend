package com.iks.cms.xml.parser;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

import org.slf4j.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class AppObjParser extends CommonParser {
  private static final Logger logger = LoggerFactory.getLogger( AppObjParser.class );
  public IAppObj parse( String fileName ) throws Exception {
    Document doc = parseFile( fileName );
    return parseRoot( doc.getDocumentElement() );
  }
  public IAppObj parse( Element root ) throws Exception {
    return parseRoot( root );
  }
  //appObj
  private IAppObj parseRoot( Element root ) throws Exception {
    AppObj appObj = new AppObj();
    appObj.setName( root.getAttribute( "name" ) );
    NodeList fieldList = root.getChildNodes();
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element childElement = ( Element )node;
        switch( childElement.getTagName() ) {
          case "data":
            appObj.setDataModel( parseDataModel( childElement ) );
            break;
          case "list":
            appObj.setGrid( parseGrid( childElement ) );
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
    DataParser dataParser = new DataParser();
    return dataParser.parse( modelElement.getAttribute( "url" ) );
  }
  //list
  private IGrid parseGrid( Element gridElement ) throws Exception {
    GridParser dataParser = new GridParser();
    return dataParser.parse( gridElement.getAttribute( "url" ) );
  }
}
