package com.iks.cms.xml.parser;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.xml.constant.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class EditViewParser extends GulParser {
  private static final Logger logger = LoggerFactory.getLogger( EditViewParser.class );
  private IDataModel model;
  public EditViewParser( IDataModel model ) {
    this.model = model;
  }
  public EditView parse( String fileName ) throws Exception {
    Document doc = parseFile( fileName );
    return parseRoot( doc );
  }
  private EditView parseRoot( Document doc ) throws Exception {
    Element root = doc.getDocumentElement();
    EditView editView = new EditView();
    parseGulContainer( editView, root );
    return editView;
  }
  @Override
  protected IDataModel getModel() {
    return model;
  }
  protected void setModel( IDataModel model ) {
    this.model = model;
  }
}
