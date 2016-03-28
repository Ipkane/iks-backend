package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ResponseGetGridData extends AbstractApiResponse {
  private PageableResult result;
  public PageableResult getResult() {
    return result;
  }
  public void setResult( PageableResult result ) {
    this.result = result;
  }
}
