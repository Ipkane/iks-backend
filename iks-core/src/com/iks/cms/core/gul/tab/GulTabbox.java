package com.iks.cms.core.gul.tab;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.grid.*;

import org.w3c.dom.*;

import java.util.*;
import java.util.stream.*;

/**
 * @author Igor Kaynov
 */
public class GulTabbox extends GulContainer {
  private GulTabs      tabs;
  private GulTabpanels tabpanels;
  @Override
  public String getTag() {
    return GulConstant.TABBOX;
  }
  @Override
  public String getTemplatePath() {
    return "gul/tab";
  }
  @Override
  public void parse( Element xmlElement ) throws Exception {
    super.parse( xmlElement );
    for( IGulElement element : elements ) {
      if( element instanceof GulTabs ) {
        tabs = ( GulTabs )element;
      } else if( element instanceof GulTabpanels ) {
        tabpanels = ( GulTabpanels )element;
      }
    }
  }
  public GulTabs getTabs() {
    return tabs;
  }
  public void setTabs( GulTabs tabs ) {
    this.tabs = tabs;
  }
  public GulTabpanels getTabpanels() {
    return tabpanels;
  }
  public void setTabpanels( GulTabpanels tabpanels ) {
    this.tabpanels = tabpanels;
  }
}
