package com.iks.cms.core.sql.query

import com.iks.cms.core.sql.*

class UpdateColumn(val column: Column, value: Any?) {
  var value: ColumnValue?

  init {
    this.value = ColumnValue(value, column.type)
  }

  override fun toString(): String {
    return column.name.quote() + "=" + value.toString()
  }
}