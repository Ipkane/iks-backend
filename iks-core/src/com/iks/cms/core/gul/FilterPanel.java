package com.iks.cms.core.gul;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class FilterPanel extends GulContainer implements IFilterPanel {
  private List< IGulInputField > fields = new ArrayList<>();
  @Override
  public List< IGulInputField > getFields() {
    return fields;
  }
  public void setFields( List< IGulInputField > fields ) {
    this.fields = fields;
  }
  public void addElement( IGulElement element ) {
    super.addElement( element );
    if( element instanceof IGulInputField ) {
      fields.add( ( IGulInputField )element );
    }
  }
}
