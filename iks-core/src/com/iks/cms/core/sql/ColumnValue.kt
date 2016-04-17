package com.iks.cms.core.sql

import org.apache.commons.lang3.*

/**
 * @author Igor Kaynov
 */
class ColumnValue(value: Any) {
  var value: Any? = null
  var type = ColumnType.String

  init {
    this.value = value
  }

  constructor(value: Any, type: ColumnType) : this(value) {
    this.type = type
  }

  override fun toString(): String {
    return value?.toString() ?: "null"
  }
}
