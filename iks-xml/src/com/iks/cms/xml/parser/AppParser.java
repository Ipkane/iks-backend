package com.iks.cms.xml.parser;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.model.*;

import org.slf4j.*;
import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class AppParser extends CommonParser {
  private static final Logger logger = LoggerFactory.getLogger( AppParser.class );
  public List< IAppObj > parse( String fileName ) throws Exception {
    Document doc = parseFile( fileName );
    return parseRoot( doc );
  }
  private List< IAppObj > parseRoot( Document doc ) throws Exception {
    Element root = doc.getDocumentElement();
    List< IAppObj > appObjList = new ArrayList<>();
    NodeList fieldList = doc.getElementsByTagName( "appObj" );
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element appObjElement = ( Element )node;
        appObjList.add( parseAppObj( appObjElement ) );
      }
    }
    return appObjList;
  }
  private IAppObj parseAppObj( Element appObjElement ) throws Exception {
    AppObjParser appObjParser = new AppObjParser();
    return appObjParser.parse( appObjElement );
  }
}
