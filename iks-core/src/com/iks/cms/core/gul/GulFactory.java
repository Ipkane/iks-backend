package com.iks.cms.core.gul;

import com.iks.cms.core.constant.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.gul.grid.*;
import com.iks.cms.core.gul.tab.*;

/**
 * @author Igor Kaynov
 */
public class GulFactory {
  public static IGulElement createElement( String tagName ) throws Exception {
    switch( tagName ) {
      case GulConstant.TEXTBOX:
        return new GulTextbox();
      case GulConstant.DATEBOX:
        return new GulDatebox();
      case GulConstant.TIMEBOX:
        return new GulTimebox();
      case GulConstant.CHECKBOX:
        return new GulCheckbox();
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
      case GulConstant.MARKUP:
        return new GulHtml();
      case GulConstant.LABEL:
        return new GulLabel();
      case GulConstant.GRID_LAYOUT:
        return new GulGridLayout();
      case GulConstant.ROWS:
        return new GulRows();
      case GulConstant.ROW:
        return new GulRow();
      case GulConstant.COLUMNS:
        return new GulColumns();
      case GulConstant.COLUMN:
        return new GulColumn();
      case GulConstant.TABBOX:
        return new GulTabbox();
      case GulConstant.TAB:
        return new GulTab();
      case ListConstant.APP_TABLE:
        return new AppGrid();
      case GulConstant.REFERENCE_FIELD:
        return new GulReferenceField();
      case ListConstant.REFERENCE_TABLE_FIELD:
        return new ReferenceTableField();
      default:
        throw new Exception( "Couldn't parse element " + tagName );
    }
  }
}
