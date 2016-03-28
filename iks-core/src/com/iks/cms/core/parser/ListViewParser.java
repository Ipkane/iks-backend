package com.iks.cms.core.parser;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.constant.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.panel.*;
import com.iks.cms.core.model.*;

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
    ParseContext context = new ParseContext();
    Element root = doc.getDocumentElement();
    ListView listView = new ListView();
    listView.parse( context, root );
    return listView;
  }
  protected IDataModel getModel() {
    return model;
  }
}
