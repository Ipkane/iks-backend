package com.iks.cms.core.sql.query

import com.iks.cms.core.sql.*

import java.util.*

/**
 * @author Igor Kaynov
 */
open abstract class AbstractChangeQuery(val table: Table) : SqlQuery() {
  var updateColumns: MutableList<UpdateColumn> = ArrayList()
  open fun addUpdateColumn(column: Column, value: Any) {
    updateColumns.add(UpdateColumn(column, value))
  }
}
