package com.iks.cms.core.gul;

import com.iks.cms.core.gul.form.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class FilterPanel extends GulContainer implements IFilterPanel {
  private List< IGulInputField > fields = new ArrayList<>();
  @Override
  public List< IGulInputField > getFields() {
    return fields;
  }
  public void setFields( List< IGulInputField > fields ) {
    this.fields = fields;
  }
  public void addElement( IGulElement element ) {
    super.addElement( element );
    if( element instanceof IGulInputField ) {
      fields.add( ( IGulInputField )element );
    }
  }
  @Override
  public String getTemplatePath() {
    return "gul/filterPanel";
  }
  @Override
  public String getTemplateName() {
    return "filterPanel";
  }
}
