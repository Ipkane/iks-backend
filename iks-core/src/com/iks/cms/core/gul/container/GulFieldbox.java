package com.iks.cms.core.gul.container;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.element.*;

import javafx.scene.layout.*;

/**
 * @author Igor Kaynov
 */
public class GulFieldbox extends GulVBox {
  public GulFieldbox() {
    super();
//    GulVBox box = new GulVBox();
//    root = box;
  }
  public void addElement( IGulElement element ) {
    element.setParent(this);
    elements.add( element );
  }
  @Override
  public String getTag() {
    return GulConstant.FIELDBOX;
  }
}
