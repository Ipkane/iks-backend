package com.iks.cms.core.gul;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class GulContainer extends GulElement implements IGulContainer {
  private List< IGulElement > elements = new ArrayList<>();
  @Override
  public List< IGulElement > getElements() {
    return elements;
  }
  public void setElements( List< IGulElement > elements ) {
    this.elements = elements;
  }
  public void addElement( IGulElement element ) {
    elements.add( element );
  }
}
