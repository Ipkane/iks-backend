package com.iks.cms.core.sql

import com.iks.cms.core.query.*

import org.apache.commons.lang3.*

/**
 * @author Igor Kaynov
 */
class MatchCriteria @JvmOverloads constructor(private val column: Column, private val value: Any?, matchType: MatchType = MatchType.Eq) : ICriteria {
  private val condition: String

  init {
    this.condition = matchType.condition
  }

  override fun toString(): String {
    val valueStr = if (value == null) null else StringUtils.trimToNull(value.toString())
    if (valueStr == null) {
      return column.toString() + " is null"
    } else {
      return column.toString() + condition + "'" + value + "'"
    }
  }
}
