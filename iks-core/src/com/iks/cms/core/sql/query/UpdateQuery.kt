package com.iks.cms.core.sql.query

import com.iks.cms.core.constant.ModelConstant
import com.iks.cms.core.data.FieldConstant
import com.iks.cms.core.query.MatchType
import com.iks.cms.core.sql.*

import java.util.*

/**
 * @author Igor Kaynov
 */
class UpdateQuery(table: Table) : AbstractChangeQuery(table) {
  private val criterias = ArrayList<ICriteria>()
  override fun addUpdateColumn(column: Column, value: Any) {
    updateColumns.add(UpdateColumn(column, value))
  }

  override fun toString(): String {
    val sql = StringBuilder("update ")
    sql.append(table.name.quote()).append(" set ")
    appendList(sql, updateColumns, "", ", ", " ")
    appendList(sql, criterias, " where ", " and ", " ")
    return sql.toString()
  }

  fun setCriteriaById(itemId: Long?) {
    criterias.add(MatchCriteria(table.getColumn(FieldConstant.DEFAULT_PRIMARY_FIELD), itemId, MatchType.Eq))
  }
}
