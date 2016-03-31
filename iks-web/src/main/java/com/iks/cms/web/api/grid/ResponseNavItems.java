package com.iks.cms.web.api.grid;

import com.fasterxml.jackson.databind.annotation.*;
import com.iks.cms.core.common.*;
import com.iks.cms.web.api.common.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
@JsonSerialize
public class ResponseNavItems extends AbstractApiResponse {
  public ResponseNavItems(List<NavItem> items) {
    this.navItems = items;
  }
  private List< NavItem > navItems;
  public List< NavItem > getNavItems() {
    return navItems;
  }
  public void setNavItems( List< NavItem > navItems ) {
    this.navItems = navItems;
  }
}
