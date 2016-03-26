package com.iks.cms.core.query;

import org.hibernate.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public abstract class CommonDaoQuery {
  public List selectQuery( SessionFactory sessionFactory, String selectQuery ) {
    Session session = sessionFactory.openSession();
    try {
      Query query = session.createSQLQuery( selectQuery );
      return query.list();
    } finally {
      session.close();
    }
  }
  public Object selectSingleQuery( SessionFactory sessionFactory, String selectQuery ) {
    Session session = sessionFactory.openSession();
    try {
      Query query = session.createSQLQuery( selectQuery );
      return query.uniqueResult();
    } finally {
      session.close();
    }
  }
  public int updateQuery( SessionFactory sessionFactory, String updateQuery ) {
    Session session = sessionFactory.openSession();
    try {
      Query query = session.createSQLQuery( updateQuery );
      return query.executeUpdate();
    } finally {
      session.close();
    }
  }
}
