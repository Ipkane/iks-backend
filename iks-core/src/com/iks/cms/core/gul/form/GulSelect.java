package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GulSelect extends GulInputField implements IGulInputField {
  private List< SelectOption > options = new ArrayList<>();
  public List< SelectOption > getOptions() {
    return options;
  }
  public void setOptions( List< SelectOption > options ) {
    this.options = options;
  }
  public void addOption( SelectOption option ) {
    options.add( option );
  }
  public String getType() {
    return GulConstant.SELECT_TYPE;
  }
}
