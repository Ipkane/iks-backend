package com.iks.cms.core.grid;

import com.fasterxml.jackson.annotation.*;
import com.iks.cms.common.json.*;
import com.iks.cms.core.appObj.*;
import com.iks.cms.core.constant.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.parser.*;
import com.iks.cms.core.utils.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class ReferenceTableField extends BaseGrid {
  @JsonView(JsonViews.Normal.class)
  private String fieldName;
  @JsonView(JsonViews.Normal.class)
  private String label;
  private SearchGrid searchGrid;

  public ReferenceTableField() {
    id = UUID.randomUUID().toString();
    //    showToolbar = false;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public void parse(IParseContext context, Element xmlElement) throws Exception {
    setAppObj(context.getAppObj());
    super.parse(context, xmlElement);
    if (xmlElement.hasAttribute(GulConstant.ATTR_FIELD_NAME)) {
      setFieldName(xmlElement.getAttribute(GulConstant.ATTR_FIELD_NAME));
    }
    if (xmlElement.hasAttribute(GulConstant.ATTR_LABEL)) {
      setLabel(xmlElement.getAttribute(GulConstant.ATTR_LABEL));
    }
    // create extra search field
    SearchGrid searchGrid = new SearchGrid();
    searchGrid.setId(id + "_search_grid");
    IDataField dataField = App.getModel(appObj).getField(fieldName);
    if (dataField instanceof ManyToMany) {
      searchGrid.setAppObj(((ManyToMany) dataField).getAppObj());
    } else if (dataField instanceof OneToMany) {
      searchGrid.setAppObj(((OneToMany) dataField).getAppObj());
    } else {
      throw new IllegalArgumentException("Data field " + dataField.getFieldName() + " must be either oneToMany or manyToMany field");
    }
    for (IGridColumn column : getColumns()) {
      GridColumn newColumn = new GridColumn(column);
      newColumn.setFieldName(ModelUtils.getFinalField(column.getFieldName()));
      searchGrid.addColumn(newColumn);
    }
    App.addGrid(searchGrid.getId(), searchGrid);
    setSearchGrid(searchGrid);
  }

  @Override
  public String getTemplatePath() {
    return "app/referenceTableField";
  }

  @Override
  public String getTag() {
    return ListConstant.REFERENCE_TABLE_FIELD;
  }

  public SearchGrid getSearchGrid() {
    return searchGrid;
  }

  public void setSearchGrid(SearchGrid searchGrid) {
    this.searchGrid = searchGrid;
  }
}
