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
  private String referenceGrid;

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
    setReferenceGrid(xmlElement.getAttribute(GulConstant.ATTR_REFERENCE_GRID));
  }

  @Override
  public String getTemplatePath() {
    return "app/referenceTableField";
  }

  @Override
  public String getTag() {
    return ListConstant.REFERENCE_TABLE_FIELD;
  }

  public String getReferenceGrid() {
    return referenceGrid;
  }

  public void setReferenceGrid(String referenceGrid) {
    this.referenceGrid = referenceGrid;
  }
}
