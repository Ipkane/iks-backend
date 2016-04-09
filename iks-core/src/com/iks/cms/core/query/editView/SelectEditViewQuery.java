package com.iks.cms.core.query.editView;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.constant.ModelConstant;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.Constants;
import com.iks.cms.core.query.model.*;

import org.slf4j.*;

/**
 * @author Igor Kaynov
 */
public class SelectEditViewQuery extends SelectModelQuery<SelectEditViewQuery> {
  private static final Logger logger = LoggerFactory.getLogger(SelectEditViewQuery.class);
  private IEditView editView;

  public SelectEditViewQuery(IDataModel model, IEditView editView, Long itemId) {
    super(model);
    this.editView = editView;
    for (IGulInputField field : editView.getFields()) {
      if (field.getFieldName() != null) {
        String fieldName = field.getFieldName();
        addField(fieldName);
      }
      if (field instanceof GulReferenceField) {
        String displayField = ((GulReferenceField) field).getDisplayField();
        addField(field.getFieldName() + Constants.FIELD_SEPARATOR + displayField);
      }
    }
    addFilter(model.getPrimaryFieldName(), itemId);
  }
}
