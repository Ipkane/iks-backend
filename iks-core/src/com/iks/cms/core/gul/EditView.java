package com.iks.cms.core.gul;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class EditView extends GulContainer implements IEditView {
  private List< IGulInputField > fields   = new ArrayList<>();
  public void addElement( IGulElement element ) {
    super.addElement( element );
    if( element instanceof IGulInputField ) {
      fields.add( ( IGulInputField )element );
    }
  }
  @Override
  public List< IGulInputField > getFields() {
    return fields;
  }
}
