package com.iks.cms.core.utils;

import com.iks.cms.core.query.*;

import java.util.regex.*;

/**
 * @author Igor Kaynov
 */
public class ModelUtils {
  public static boolean isSimpleField(String fieldName) {
    return !fieldName.contains( Constants.FIELD_SEPARATOR );
  }
  /**
   * Splites fieldName to parts, such as base Field name and referenced field name
   * @param fieldName
   * @return
   */
  public static String[] splitField(String fieldName) {
    return fieldName.split( Pattern.quote( Constants.FIELD_SEPARATOR ));
  }
}
