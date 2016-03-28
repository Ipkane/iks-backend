package com.iks.cms.core.parser;

import com.iks.cms.core.constant.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class ModelParser {
  private static final Logger logger = LoggerFactory.getLogger( ModelParser.class );
  public IDataModel parse( String fileName ) throws Exception {
    Document doc = ParserUtils.parseFile( fileName );
    return parseRoot( doc );
  }
  private IDataModel parseRoot( Document doc ) throws Exception {
    Element root = doc.getDocumentElement();
    BaseDataModel model = new BaseDataModel();
    model.setAppObj( root.getAttribute( ModelConstant.APP_OBJ_ATTR ) );
    model.setTableName( root.getAttribute( ModelConstant.TABLE_NAME_ATTR ) );
    NodeList fieldList = root.getChildNodes();
    for( int i = 0; i < fieldList.getLength(); i++ ) {
      Node node = fieldList.item( i );
      if( node.getNodeType() == Node.ELEMENT_NODE ) {
        Element xmlElement = ( Element )node;
        IDataField field = ModelFactory.createField( xmlElement.getTagName() );
        field.parse( xmlElement );
        model.addField( field );
      }
    }
    return model;
  }
}
