package com.iks.cms.core.gul;

import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.form.*;

/**
 * @author Igor Kaynov
 */
public class GulFactory {
  public static IGulElement createElement( String tagName ) throws Exception {
    switch( tagName ) {
      case GulConstant.TEXTBOX:
        return new GulTextbox();
      case GulConstant.SELECT:
        return new GulSelect();
      case GulConstant.REFERENCE_SELECT:
        return new GulReferenceSelect();
      case GulConstant.BUTTON:
        return new GulButton();
      case GulConstant.BOX:
        return new GulBox();
      case GulConstant.HBOX:
        return new GulHBox();
      case GulConstant.VBOX:
        return new GulVBox();
      case GulConstant.SPACER:
        return new GulSpacer();
      case GulConstant.FIELDBOX:
        return new GulFieldbox();
      case GulConstant.SCRIPT:
        return new GulScript();
      default:
        throw new Exception( "Couldn't parse element " + tagName );
    }
  }
}
