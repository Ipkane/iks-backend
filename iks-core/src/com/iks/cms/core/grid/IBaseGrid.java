package com.iks.cms.core.grid;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IBaseGrid {
  String getId();
  String getAppObj();
  List<IGridColumn > getColumns();
}
