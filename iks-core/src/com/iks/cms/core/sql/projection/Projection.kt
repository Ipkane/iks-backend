package com.iks.cms.core.sql.projection

import com.iks.cms.core.sql.*

/**
 * @author Igor Kaynov
 */
class Projection(var query: String?, private val column: Column?) : IProjection {
  override fun toString(): String {
    return query + "(" + (column ?: "*") + ")"
  }
}
