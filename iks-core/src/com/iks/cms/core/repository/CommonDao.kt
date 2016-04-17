package com.iks.cms.core.repository

import org.hibernate.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

import java.util.*

/**
 * @author Igor Kaynov
 */
@Component
open class CommonDao {
  @Autowired
  lateinit var sessionFactory: SessionFactory

  val session: Session
    get() = sessionFactory.openSession()

  fun selectQuery(selectQuery: String): List<Any?> {
    val session = session
    val query = session.createSQLQuery(selectQuery)
    session.close()
    return query.list()
  }

  fun selectSingleQuery(selectQuery: String): Any {
    val session = session
    val query = session.createSQLQuery(selectQuery)
    session.close()
    return query.uniqueResult()
  }

  fun updateQuery(updateQuery: String): Int {
    val session = session
    val query = session.createSQLQuery(updateQuery)
    session.close()
    return query.executeUpdate()
  }
}
