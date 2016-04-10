package com.iks.cms.core.grid;

import com.fasterxml.jackson.annotation.JsonView;
import com.iks.cms.common.json.JsonViews;

/**
 * @author Igor Kaynov
 */
public interface IGridColumn {
  @JsonView( JsonViews.Normal.class )
  public String getFieldName();
  @JsonView( JsonViews.Normal.class )
  public String getLabel();
  public String getDisplayField();
}
