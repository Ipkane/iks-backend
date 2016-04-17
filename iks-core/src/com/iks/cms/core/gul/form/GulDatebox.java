package com.iks.cms.core.gul.form;

import com.iks.cms.core.data.IDataField;
import com.iks.cms.core.gul.GulConstant;
import com.iks.cms.core.parser.IParseContext;
import org.apache.commons.lang3.BooleanUtils;
import org.w3c.dom.Element;

/**
 * @author Igor Kaynov
 */
public class GulDatebox extends GulTextbox {
  @Override
  public String getTag() {
    return GulConstant.DATEBOX;
  }
}
