package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;

/**
 * @author Igor Kaynov
 */
public abstract class AbstractGulField extends GulElement implements IGulInputField {
  private String          name;
  private String          label;
//  private IFieldContainer fieldContainer;
  @Override
  public void setParent( IGulContainer parent ) {
    super.setParent( parent );
    IGulContainer superParent = parent;
    while( superParent != null ) {
      if( superParent instanceof IFieldContainer ) {
        ( ( IFieldContainer )superParent ).addField( this );
//        fieldContainer = ( IFieldContainer )superParent;
        break;
      }
      superParent = superParent.getParent();
    }
  }
//  public void setFieldContainer(IFieldContainer fieldContainer) {
//    if( this.fieldContainer != null ) {
//      this.fieldContainer.removeField( this );
//    }
//    this.fieldContainer = fieldContainer;
//  }
  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
}
