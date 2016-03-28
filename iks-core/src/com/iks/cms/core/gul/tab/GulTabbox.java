package com.iks.cms.core.gul.tab;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.grid.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

import java.util.*;
import java.util.stream.*;

/**
 * @author Igor Kaynov
 */
public class GulTabbox extends GulContainer {
  private List< GulTab > tabs = new ArrayList<>();
  @Override
  public String getTag() {
    return GulConstant.TABBOX;
  }
  @Override
  public String getTemplatePath() {
    return "gul/tab";
  }
  @Override
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
    for( IGulElement element : elements ) {
      if( element instanceof GulTab ) {
        tabs.add( ( GulTab )element );
      }
    }
  }
  public List<GulTab> getTabs() {
    return tabs;
  }
  public void setTabs(GulTab tab) {
    tabs.add( tab );
  }
}
