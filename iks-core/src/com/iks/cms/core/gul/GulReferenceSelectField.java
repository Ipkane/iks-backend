package com.iks.cms.core.gul;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GulReferenceSelectField extends GulReferenceField {
  private List< SelectOption > options = new ArrayList<>();
  public List< SelectOption > getOptions() {
    return options;
  }
  public void setOptions( List< SelectOption > options ) {
    this.options = options;
  }
  public void addOption(SelectOption option) {
    options.add( option );
  }
  @Override
  public String getType() {
    return GulConstant.REFERENCE_SELECT_TYPE;
  }
}
