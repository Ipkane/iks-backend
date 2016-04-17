package com.iks.cms.core.sql.query

import com.iks.cms.core.sql.*

import java.util.*

/**
 * @author Igor Kaynov
 */
class DeleteQuery(val table: Table) : SqlQuery() {
  private val criterias = ArrayList<ICriteria>()
  fun addCriteria(criteria: ICriteria) {
    criterias.add(criteria)
  }

  override fun toString(): String {
    val sql = StringBuilder("delete from ")
    sql.append(table.name)
    appendList(sql, criterias, " where ", " and ", "")
    return sql.toString()
  }
}
