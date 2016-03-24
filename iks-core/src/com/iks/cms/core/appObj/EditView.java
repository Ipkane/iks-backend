package com.iks.cms.core.appObj;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.form.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class EditView extends GulFieldContainer implements IEditView {
  @Override
  public String getTemplatePath() {
    return "view/editView";
  }
  @Override
  public String getTag() {
    return GulConstant.EDIT_VIEW;
  }
}
