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
  public List selectQuery(String selectQuery) {
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createSQLQuery( selectQuery );
      return query.list();
    }
  }
}
