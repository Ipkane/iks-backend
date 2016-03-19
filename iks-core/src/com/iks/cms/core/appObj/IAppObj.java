package com.iks.cms.core.appObj;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public interface IAppObj {
  String getName();
  IDataModel getDataModel();
  IGrid getGrid();
  IEditView getEditView();
}
