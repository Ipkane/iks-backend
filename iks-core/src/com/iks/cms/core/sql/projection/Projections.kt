package com.iks.cms.core.sql.projection

import com.iks.cms.core.sql.*

/**
 * @author Igor Kaynov
 */
object Projections {
  fun rowCount(column: Column): IProjection {
    return Projection("count", column)
  }

  fun rowCount(): IProjection {
    return Projection("count", null)
  }
}
