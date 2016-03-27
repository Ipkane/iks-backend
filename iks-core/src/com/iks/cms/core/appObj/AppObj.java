package com.iks.cms.core.appObj;

import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public class AppObj implements IAppObj {
  private String     name;
  private String     label;
  private IDataModel dataModel;
  private IListView  gridView;
  private EditView   editView;
  public IDataModel getDataModel() {
    return dataModel;
  }
  public void setDataModel( IDataModel dataModel ) {
    this.dataModel = dataModel;
  }
  public IGrid getGrid() {
    return gridView.getGrid();
  }
  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
  @Override
  public EditView getEditView() {
    return editView;
  }
  public void setEditView( EditView editView ) {
    this.editView = editView;
  }
  public IListView getListView() {
    return gridView;
  }
  public void setGridView( IListView gridView ) {
    this.gridView = gridView;
  }
}
