package com.iks.cms.core.sql

import java.util.*

/**
 * @author Igor Kaynov
 */
class Column(val name: String) {
  var tableAlias: String? = null
  var alias: String? = null
  var type = ColumnType.String

  constructor(table: Table, name: String) : this(table.alias, name, null) {
  }

  constructor(table: Table, name: String, alias: String?) : this(table.alias, name, alias) {
  }

  constructor(tableAlias: String?, name: String, alias: String?) : this(name) {
    this.tableAlias = tableAlias
    this.alias = alias
  }

  override fun toString(): String {
    val sb = StringBuilder()
    if (tableAlias != null) {
      sb.append(tableAlias.quote()).append('.')
    }
    sb.append(name.quote())
    //    if( alias != null ) {
    //      sb.append( " as " ).append( alias );
    //    }
    return sb.toString()
  }

  override fun equals(other: Any?): Boolean {
    if (other == null || other !is Column) {
      return false
    }
    return tableAlias == other.tableAlias && name == other.name
  }
}
