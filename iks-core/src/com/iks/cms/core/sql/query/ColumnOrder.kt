package com.iks.cms.core.sql.query

import com.iks.cms.core.sql.*

/**
 * @author Igor Kaynov
 */
class ColumnOrder(var column: Column, var order: EColumnOrder = EColumnOrder.ASC) {
  override fun toString(): String {
    return column.toString() + " " + order.toString()
  }
}
