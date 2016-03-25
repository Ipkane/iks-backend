package com.iks.cms.core.gul.panel;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GulFilterPanel extends GulFieldContainer implements IGulFilterPanel {
  public GulFilterPanel( IDataModel model ) {
    super( model );
  }
  @Override
  public String getTag() {
    return GulConstant.FILTER_PANEL;
  }
  @Override
  public String getTemplatePath() {
    return "gul/filterPanel";
  }
  @Override
  public String getTemplateName() {
    return "filterPanel";
  }
}
