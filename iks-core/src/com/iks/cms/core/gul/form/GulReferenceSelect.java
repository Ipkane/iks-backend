package com.iks.cms.core.gul.form;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GulReferenceSelect extends GulReferenceField {
  private List< SelectOption > options = new ArrayList<>();
  @Override
  public String getTag() {
    return GulConstant.REFERENCE_SELECT;
  }
  public List< SelectOption > getOptions() {
    return options;
  }
  public void setOptions( List< SelectOption > options ) {
    this.options = options;
  }
  public void addOption( SelectOption option ) {
    options.add( option );
  }
  @Override
  public String getTemplatePath() {
    return "gul/referenceSelect";
  }
  @Override
  public void parse(IParseContext context,  Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
  }
}
