package com.iks.cms.core.service;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IGridRequest {
  String getGridId();
  Map< String, Object > getFilter();
  String getOrderBy();
  Integer getPage();
  Integer getLimit();
}
