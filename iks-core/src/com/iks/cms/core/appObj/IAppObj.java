package com.iks.cms.core.appObj;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public interface IAppObj {
  String getName();
  String getLabel();
  IDataModel getDataModel();
  IListView getListView();
  IEditView getEditView();
}
