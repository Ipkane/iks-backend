package com.iks.cms.core.sql.join

import com.iks.cms.core.sql.*

/**
 * @author Igor Kaynov
 */
class Join(var table: Table?, var firstColumn: Column?, var secondColumn: Column?) {
  var joinType = JoinType.Left
  override fun toString(): String {
    val sb = StringBuilder()
    sb.append(table).append(" on ").append(firstColumn).append("=").append(secondColumn)
    return sb.toString()
  }

  override fun equals(other: Any?): Boolean {
    if (other == null || other !is Join) {
      return false
    }
    return table == other.table
        && firstColumn == other.firstColumn
        && secondColumn == other.secondColumn
        && joinType == other.joinType
  }
}
