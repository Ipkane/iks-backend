package com.iks.cms.core.validation;

import com.iks.cms.core.exception.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

/**
 * @author Igor Kaynov
 */
public interface IValidator {
  boolean validate(IDataModel model, IDataItem item);
  IValidationError getError();

}
