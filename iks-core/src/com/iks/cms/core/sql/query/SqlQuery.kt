package com.iks.cms.core.sql.query

import java.util.*

/**
 * @author Igor Kaynov
 */
abstract class SqlQuery {
  protected fun appendList(sql: StringBuilder, list: List<Any?>, init: String, sep: String, end: String?) {
    var first = true
    for (s in list) {
      if (first) {
        sql.append(init)
      } else {
        sql.append(sep)
      }
      sql.append(s?.toString())
      first = false
    }
    if (end != null) {
      sql.append(end)
    }
  }
}
