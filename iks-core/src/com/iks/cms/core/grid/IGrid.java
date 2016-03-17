package com.iks.cms.core.grid;

import com.iks.cms.core.model.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IGrid {
  List<IGridField> getFields();
  IDataModel getDataModel();
}
