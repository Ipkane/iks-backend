package com.iks.cms.core.gul;

import com.iks.cms.core.grid.*;

/**
 * @author Igor Kaynov
 */
public class GridView extends GulContainer implements IGridView {
  private IGrid        grid;
  private IFilterPanel filterPanel;
  public IGrid getGrid() {
    return grid;
  }
  public void setGrid( IGrid grid ) {
    this.grid = grid;
  }
  public IFilterPanel getFilterPanel() {
    return filterPanel;
  }
  public void setFilterPanel( IFilterPanel filterPanel ) {
    this.filterPanel = filterPanel;
  }
}
