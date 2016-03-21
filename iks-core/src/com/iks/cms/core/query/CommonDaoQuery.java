package com.iks.cms.core.query;

import org.hibernate.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class CommonDaoQuery {
  public List selectQuery( SessionFactory sessionFactory, String selectQuery ) {
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createSQLQuery( selectQuery );
      return query.list();
    }
  }
  public Object selectSingleQuery( SessionFactory sessionFactory, String selectQuery ) {
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createSQLQuery( selectQuery );
      return query.uniqueResult();
    }
  }
  public int updateQuery( SessionFactory sessionFactory, String updateQuery ) {
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createSQLQuery( updateQuery );
      return query.executeUpdate();
    }
  }
}
