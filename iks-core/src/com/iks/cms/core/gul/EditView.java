package com.iks.cms.core.gul;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class EditView implements IEditView {
  private List< IGulElement > elements = new ArrayList<>();
  private List< IGulInput >   fields   = new ArrayList<>();
  public List< IGulElement > getElements() {
    return elements;
  }
  public void setElements( List< IGulElement > elements ) {
    this.elements = elements;
  }
  public void addElement( IGulElement element ) {
    elements.add( element );
    if( element instanceof IGulInput ) {
      fields.add( ( IGulInput )element );
    }
  }
  @Override
  public List< IGulInput > getFields() {
    return fields;
  }
}
