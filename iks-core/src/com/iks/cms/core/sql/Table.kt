package com.iks.cms.core.sql

import java.util.*

/**
 * @author Igor Kaynov
 */
class Table(name: String) {
  var name: String
  var alias: String? = null
  private val columns = HashMap<String, Column>()

  init {
    this.name = name;
  }
  
  constructor(name: String, alias: String) : this(name) {
    this.alias = alias
  }

  override fun toString(): String {
    return if (alias != null) name.quote() + " as " + alias.quote() else name.quote()
  }

  @JvmOverloads fun getColumn(fieldName: String, fieldAlias: String? = null): Column {
    var column: Column? = columns[fieldName]
    if (column == null) {
      column = Column(this, fieldName, fieldAlias)
      columns.put(fieldName, column)
    }
    return column
  }

  override fun equals(other: Any?): Boolean {
    if (other == null || other !is Table) {
      return false
    }
    return name == other.name
  }

  companion object {
    private val counter = 0
  }
}
