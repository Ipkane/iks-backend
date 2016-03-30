package com.iks.cms.core.query.editView;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.model.*;

import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class CreateEditViewQuery extends CreateModelQuery<CreateEditViewQuery > {
  private static final Logger logger = LoggerFactory.getLogger( SelectEditViewQuery.class );
  private IEditView  editView;

  public CreateEditViewQuery( IDataModel model, IEditView editView, IDataItem item ) {
    super(model, item);
    for( IGulInputField field : editView.getFields() ) {

      addField( field.getFieldName() );
    }
    this.editView = editView;
  }
}
