package com.iks.cms.core.service;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IGridRequest {
  String getGridId();
  Map< String, Object > getFilter();
  String getSortField();
  String getSortDir();
  Integer getPage();
  Integer getLimit();
  String getParentId();
}
