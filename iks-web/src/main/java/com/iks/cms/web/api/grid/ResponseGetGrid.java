package com.iks.cms.web.api.grid;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;

/**
 * @author Igor Kaynov
 */
public class ResponseGetGrid extends AbstractApiResponse {
  private IGrid grid;
  public IGrid getGrid() {
    return grid;
  }
  public void setGrid( IGrid grid ) {
    this.grid = grid;
  }
}
