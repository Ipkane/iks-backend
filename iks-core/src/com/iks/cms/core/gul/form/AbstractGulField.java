package com.iks.cms.core.gul.form;

import com.iks.cms.core.data.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

/**
 * @author Igor Kaynov
 */
public abstract class AbstractGulField extends GulElement implements IGulInputField {
  private String name;
  private String label;
  private boolean readonly     = false;
  //  private IFieldContainer fieldContainer;
  @Override
  public void setParent( IGulContainer parent ) {
    super.setParent( parent );
    IGulContainer superParent = parent;
    while( superParent != null ) {
      if( superParent instanceof IFieldContainer ) {
        ( ( IFieldContainer )superParent ).addField( this );
        //        fieldContainer = ( IFieldContainer )superParent;
        break;
      }
      superParent = superParent.getParent();
    }
  }
  //  public void setFieldContainer(IFieldContainer fieldContainer) {
  //    if( this.fieldContainer != null ) {
  //      this.fieldContainer.removeField( this );
  //    }
  //    this.fieldContainer = fieldContainer;
  //  }
  @Override
  public void parse( Element xmlElement ) throws Exception {
    super.parse(xmlElement );
    if( xmlElement.hasAttribute( GulConstant.ATTR_NAME ) ) {
      setName( xmlElement.getAttribute( GulConstant.ATTR_NAME ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_LABEL ) ) {
      setLabel( xmlElement.getAttribute( GulConstant.ATTR_LABEL ) );
    }
    if( xmlElement.hasAttribute( GulConstant.ATTR_READONLY ) ) {
      setReadonly( BooleanUtils.toBoolean( xmlElement.getAttribute( GulConstant.ATTR_READONLY ) ) );
    }
  }
  public String getName() {
    return StringUtils.trimToNull(name);
  }
  public void setName( String name ) {
    this.name = name;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel( String label ) {
    this.label = label;
  }
  public void applyModel( IDataField dataField ) {
    if( label == null ) {
      label = dataField.getLabel();
    }
  }
  public boolean isReadonly() {
    return readonly;
  }
  public void setReadonly( boolean readonly ) {
    this.readonly = readonly;
  }

}
