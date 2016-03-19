package com.iks.cms.web.repository;

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
    Session session = null;
    try {
      session = sessionFactory.openSession();
      Query query = session.createSQLQuery( selectQuery );
      return query.list();
    } finally {
      if( session != null ) {
        session.close();
      }
    }
  }
}
