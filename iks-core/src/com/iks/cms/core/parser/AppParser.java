package com.iks.cms.core.parser;

import com.iks.cms.core.appObj.*;

import org.slf4j.*;
import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class AppParser {
  private static final Logger logger = LoggerFactory.getLogger( AppParser.class );
  public List< IAppObj > parse( String fileName ) throws Exception {
    Document doc = ParserUtils.parseFile( fileName );
    return parseRoot( doc );
  }
  // app
  private List< IAppObj > parseRoot( Document doc ) throws Exception {
    Element root = doc.getDocumentElement();
    List< IAppObj > appObjList = new ArrayList<>();
    NodeList fieldList = doc.getElementsByTagName( "appObj" );
    // app parsed in two steps. in first step it collects all models, than other types
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        try {
          Element appObjElement = ( Element )node;
          IAppObj appObj = parseAppObj( appObjElement, true );
          appObjList.add( appObj );
          logger.debug( "Parsed model {}", appObj.getName() );
        } catch(Exception e) {
          logger.error("Error parsing app obj: ", e);
        }
      }
    }
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        try {
          Element appObjElement = ( Element )node;
          IAppObj appObj = parseAppObj( appObjElement, false );
          appObjList.add( appObj );
          logger.debug( "Parsed appObj {}", appObj.getName() );
        } catch(Exception e) {
          logger.error("Error parsing app obj: ", e);
        }
      }
    }
    return appObjList;
  }
  // appObj
  private IAppObj parseAppObj( Element appObjElement, boolean parseModel ) throws Exception {
    AppObjParser appObjParser = new AppObjParser();
    return appObjParser.parse( appObjElement, parseModel );
  }
}
