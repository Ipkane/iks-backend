package com.iks.cms.core.parser;

import com.iks.cms.core.constant.*;
import com.iks.cms.core.data.*;

/**
 * @author Igor Kaynov
 */
public class ModelFactory {
  public static IDataField createField(String tagName) throws Exception {
    switch( tagName  ) {
      case ModelConstant.FIELD:
        return new InputDataField();
      case ModelConstant.SELECT_FIELD:
        return new SelectField();
      case ModelConstant.MANY_TO_ONE: {
        return new ManyToOne();
      }
      case ModelConstant.ONE_TO_ONE: {
        return new OneToOne();
      }
      case ModelConstant.ONE_TO_MANY: {
        return new ManyToMany();
      }
      case ModelConstant.MANY_TO_MANY: {
        return new ManyToMany();
      }
      default:
        throw new Exception( "Couldn't parse element " + tagName );
    }
  }
}
