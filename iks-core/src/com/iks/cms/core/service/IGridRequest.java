package com.iks.cms.core.service;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IGridRequest {
  String getAppObj();
  Map< String, Object > getFilter();
  String getOrderBy();
  Integer getPage();
  Integer getLimit();
}
