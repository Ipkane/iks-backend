package com.iks.cms.core.query;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;

import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class SelectEditViewQuery extends SelectModelQuery< SelectEditViewQuery > {
  private static final Logger logger = LoggerFactory.getLogger( SelectEditViewQuery.class );
  private IEditView editView;
  public SelectEditViewQuery( IDataModel model, IEditView editView, Long itemId ) {
    super( model );
    this.editView = editView;
    for( IGulInputField field : editView.getFields() ) {
      if (field.getName() != null) {
        addField( field.getName() );
      }
    }
    addFilter( model.getPrimaryFieldName(), itemId );
  }
}
