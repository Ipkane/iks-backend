package com.iks.cms.core.sql.query

import com.iks.cms.core.sql.*
import com.iks.cms.core.sql.join.*
import com.iks.cms.core.sql.projection.*

import java.util.*

/**
 * @author Igor Kaynov
 */
class SelectQuery : SqlQuery {
  val columns = ArrayList<Column>()
  val projections = ArrayList<IProjection>()
  val tables = ArrayList<Table>()
  val leftJoins = ArrayList<Join>()
  val criterias = ArrayList<ICriteria>()
  val orderBys = ArrayList<ColumnOrder>()
  val groupBys = ArrayList<Column>()
  val havings = ArrayList<String>()
  var limit: Int? = 0
  var offset: Int? = 0

  val mainTable: Table
    get() = tables[0]

  constructor()

  constructor(table: Table) {
    tables.add(table)
  }

  fun addColumn(column: Column): SelectQuery {
    columns.add(column)
    return this
  }

  fun addColumn(column: Column, groupBy: Boolean): SelectQuery {
    addColumn(column)
    if (groupBy) {
      groupBys.add(column)
    }
    return this
  }

  fun addProjection(projection: IProjection): SelectQuery {
    projections.add(projection)
    return this
  }

  fun from(table: Table): SelectQuery {
    tables.add(table)
    return this
  }

  fun groupBy(column: Column): SelectQuery {
    groupBys.add(column)
    return this
  }

  fun having(expr: String): SelectQuery {
    havings.add(expr)
    return this
  }

  fun leftJoin(join: Join): SelectQuery {
    if (!leftJoins.contains(join)) {
      leftJoins.add(join)
    }
    return this
  }

  fun orderBy(columnOrder: ColumnOrder): SelectQuery {
    orderBys.add(columnOrder)
    return this
  }

  override fun toString(): String {
    val sql = StringBuilder("select ")
    appendList(sql, columns, " ", ", ", "")
    appendList(sql, projections, " ", ", ", "")
    if (columns.size == 0 && projections.size == 0) {
      sql.append("*")
    }
    appendList(sql, tables, " from ", ", ", "")
    appendList(sql, leftJoins, " left join ", " left join ", "")
    appendList(sql, criterias, " where ", " and ", "")
    appendList(sql, groupBys, " group by ", ", ", "")
    appendList(sql, havings, " having ", " and ", "")
    appendList(sql, orderBys, " order by ", ", ", "")
      if (limit != null && limit!! > 0)
      sql.append(" limit $limit")

    if (offset != null && offset!! > 0) {
      sql.append(" offset $offset")
    }
    return sql.toString()
  }

  fun addCriteria(criteria: ICriteria): SelectQuery {
    criterias.add(criteria)
    return this
  }

  fun getLeftJoins(): List<Join> {
    return leftJoins
  }

  fun getOrderBys(): List<ColumnOrder> {
    return orderBys
  }
}
