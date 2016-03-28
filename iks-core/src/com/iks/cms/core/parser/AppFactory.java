package com.iks.cms.core.parser;

import com.iks.cms.core.constant.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.panel.*;

/**
 * @author Igor Kaynov
 */
public class AppFactory {
  public static IGulElement createElement( String tagName ) throws Exception {
    switch( tagName ) {
      case ListConstant.COLUMN:
        return new GridColumn();
      case ListConstant.FILTER_PANEL:
        return new FilterPanel();
      default:
        throw new Exception( "Couldn't parse element " + tagName );
    }
  }
}
