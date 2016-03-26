package com.iks.cms.core.repository;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
@Repository
public class CommonDao {
  @Autowired
  private SessionFactory sessionFactory;
  public List selectQuery( String selectQuery ) {
    Session session = getSession();
    Query query = session.createSQLQuery( selectQuery );
    session.close();
    return query.list();
  }
  public Object selectSingleQuery( String selectQuery ) {
    Session session = getSession();
    Query query = session.createSQLQuery( selectQuery );
    session.close();
    return query.uniqueResult();
  }
  public int updateQuery( String updateQuery ) {
    Session session = getSession();
    Query query = session.createSQLQuery( updateQuery );
    session.close();
    return query.executeUpdate();
  }
  public Session getSession() {
    return sessionFactory.openSession();
  }
  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
