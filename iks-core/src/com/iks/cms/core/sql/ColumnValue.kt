package com.iks.cms.core.sql

import org.apache.commons.lang3.*

/**
 * @author Igor Kaynov
 */
class ColumnValue(var value: Any?, var type: ColumnType = ColumnType.String) {

  override fun toString(): String {
    return if (type == ColumnType.String)
      value?.toString().valueQuote()
    else
      value?.toString() ?: "null"
  }
}
