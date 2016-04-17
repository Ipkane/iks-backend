package com.iks.cms.core.sql.query

import com.iks.cms.core.sql.*

import java.util.*

/**
 * @author Igor Kaynov
 */
class InsertQuery(table:Table) : AbstractChangeQuery(table) {
  override fun toString(): String {
    val columns = ArrayList<Column>()
    val values = ArrayList<Any?>()
    for (updateColumn in updateColumns) {
      columns.add(updateColumn.column)
      values.add(updateColumn.value?.value)
    }
    val sql = StringBuilder("insert into ")
    sql.append(table.name)
    appendList(sql, columns, " (", ", ", ") ")
    sql.append("values")
    appendList(sql, values, " (", ", ", ") ")
    return sql.toString()
  }

}
