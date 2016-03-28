package com.iks.cms.core.gul.form;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class GulSelect extends GulTextbox {
  @Override
  public String getTag() {
    return GulConstant.SELECT;
  }
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
  @Override
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
  }
  public void applyModel( IDataField dataField ) {
    super.applyModel( dataField );
    if( options.size() == 0 && dataField instanceof SelectField ) {
      setOptions( ( ( SelectField )dataField ).getOptions() );
    }
  }
}
