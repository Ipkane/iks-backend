package com.iks.cms.web.repository;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
@Repository
public class EmployeeDao {
  @Autowired
  private SessionFactory sessionFactory;
  public List getEmployees() {
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createSQLQuery( "select * from employee" );
      return query.list();
    }
  }
}
