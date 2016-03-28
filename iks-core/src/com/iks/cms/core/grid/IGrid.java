package com.iks.cms.core.grid;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IGrid {
  String getId();
  String getAppObj();
  List<IGridColumn > getFields();
}
