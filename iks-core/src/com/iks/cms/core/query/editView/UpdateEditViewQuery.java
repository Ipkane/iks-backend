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
public class UpdateEditViewQuery extends UpdateModelQuery<UpdateEditViewQuery > {
  private static final Logger logger = LoggerFactory.getLogger( UpdateEditViewQuery.class );
  private IEditView editView;
  private Long itemId;
  public UpdateEditViewQuery( IDataModel model, IEditView editView, IDataItem item, Long itemId ) {
    super( model, item, itemId );
    this.editView = editView;
    this.itemId = itemId;
    for( IGulInputField field : editView.getFields() ) {
      if (field.getFieldName() != null) {
        addField( field.getFieldName() );
      }
    }
    setItem( item );
  }
}
