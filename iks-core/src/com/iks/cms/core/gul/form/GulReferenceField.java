package com.iks.cms.core.gul.form;

import com.iks.cms.core.appObj.App;
import com.iks.cms.core.data.IDataField;
import com.iks.cms.core.data.ManyToMany;
import com.iks.cms.core.data.ManyToOne;
import com.iks.cms.core.data.OneToMany;
import com.iks.cms.core.grid.GridColumn;
import com.iks.cms.core.grid.IGridColumn;
import com.iks.cms.core.grid.SearchGrid;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

import com.iks.cms.core.utils.ModelUtils;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public class GulReferenceField extends GulTextbox {
  private String displayField;
  private String referenceGrid;

  public String getDisplayField() {
    return displayField;
  }

  public void setDisplayField(String displayField) {
    this.displayField = displayField;
  }

  @Override
  public String getTag() {
    return GulConstant.REFERENCE_FIELD;
  }

  @Override
  public void parse(IParseContext context, Element xmlElement) throws Exception {
    super.parse(context, xmlElement);
    setDisplayField(xmlElement.getAttribute(GulConstant.ATTR_DISPLAY_FIELD));
    setReferenceGrid(xmlElement.getAttribute(GulConstant.ATTR_REFERENCE_GRID));
  }

  public String getReferenceGrid() {
    return referenceGrid;
  }

  public void setReferenceGrid(String referenceGrid) {
    this.referenceGrid = referenceGrid;
  }
}
