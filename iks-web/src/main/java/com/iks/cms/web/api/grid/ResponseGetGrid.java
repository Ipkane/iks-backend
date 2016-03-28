package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class ResponseGetGrid extends AbstractApiResponse {
  private IBaseGrid grid;
  public IBaseGrid getGrid() {
    return grid;
  }
  public void setGrid( IBaseGrid grid ) {
    this.grid = grid;
  }
}
