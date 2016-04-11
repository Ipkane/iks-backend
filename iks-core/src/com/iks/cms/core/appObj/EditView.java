package com.iks.cms.core.appObj;

import com.fasterxml.jackson.annotation.JsonView;
import com.iks.cms.common.json.JsonViews;
import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.IParseContext;
import org.w3c.dom.Element;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class EditView extends GulFieldContainer implements IEditView {
  @JsonView(JsonViews.Normal.class)
  private int width;
  @JsonView(JsonViews.Normal.class)
  private int height;

  public EditView(IDataModel model) {
    super(model);
  }

  public EditView() {
    super();
  }

  @Override
  public String getTemplatePath() {
    return "view/editView";
  }

  @Override
  public String getTag() {
    return GulConstant.EDIT_VIEW;
  }

  @Override
  public void parse(IParseContext context, Element xmlElement) throws Exception {
    super.parse(context, xmlElement);
    if (xmlElement.hasAttribute(GulConstant.ATTR_WIDTH)) {
      setWidth(Integer.valueOf(xmlElement.getAttribute(GulConstant.ATTR_WIDTH)));
    }
    if (xmlElement.hasAttribute(GulConstant.ATTR_HEIGHT)) {
      setHeight(Integer.valueOf(xmlElement.getAttribute(GulConstant.ATTR_HEIGHT)));
    }
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }
}
