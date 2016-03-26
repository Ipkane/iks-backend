package com.iks.cms.core.query;

import org.hibernate.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class CommonDaoQuery {
  public List selectQuery( SessionFactory sessionFactory, String selectQuery ) {
    Session session = sessionFactory.openSession();
    Query query = session.createSQLQuery( selectQuery );
    session.close();
    return query.list();
  }
  public Object selectSingleQuery( SessionFactory sessionFactory, String selectQuery ) {
    Session session = sessionFactory.openSession();
    Query query = session.createSQLQuery( selectQuery );
    session.close();
    return query.uniqueResult();
  }
  public int updateQuery( SessionFactory sessionFactory, String updateQuery ) {
    Session session = sessionFactory.openSession();
    Query query = session.createSQLQuery( updateQuery );
    session.close();
    return query.executeUpdate();
  }
}
