package com.iks.cms.core.sql;

/**
 * @author Igor Kaynov
 */
public class SqlUtils {
  public static String quote(String name) {
    return "\"" + name + "\"";
  }
}
