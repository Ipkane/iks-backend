package com.iks.cms.web.config;

/*
** config data source , entity manager here
*/

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.*;
import org.springframework.orm.hibernate4.*;
import org.springframework.transaction.annotation.*;

import javax.sql.*;

@Configuration
//@EnableJpaRepositories( basePackages = { "com.makble.springmvcstart.repository" } )
@EnableTransactionManagement
@ComponentScan( basePackages = { "com.iks.cms.web.service", "com.iks.cms.web.repository", "com.iks.cms.web.controller" } )
public class BasicConfig {
  @Autowired
  private DataSource dataSource;
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName( "org.postgresql.Driver" );
    dataSource.setUrl( "jdbc:postgresql://localhost:5432/cms" );
    dataSource.setUsername( "postgres" );
    dataSource.setPassword( "postgres" );
    return dataSource;
  }
  @Bean
  public SessionFactory sessionFactory() {
    return new LocalSessionFactoryBuilder( dataSource() ).buildSessionFactory();
  }
  @Bean
  public HibernateTransactionManager hibTransMan(){
    return new HibernateTransactionManager(sessionFactory());
  }
//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    vendorAdapter.setDatabase( Database.POSTGRESQL );
//    vendorAdapter.setGenerateDdl( true );
//    vendorAdapter.setShowSql( true );
//    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//    factory.setJpaVendorAdapter( vendorAdapter );
//    factory.setPackagesToScan( getClass().getPackage().getName() );
//    factory.setDataSource( dataSource() );
//    factory.setJpaProperties( jpaProperties() );
//    return factory;
//  }
//  private Properties jpaProperties() {
//    Properties properties = new Properties();
//    properties.put( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
//    properties.put( "hibernate.show_sql", "false" );
//    return properties;
//  }
//  @Bean
//  public PlatformTransactionManager transactionManager() {
//    JpaTransactionManager txManager = new JpaTransactionManager();
//    txManager.setEntityManagerFactory( entityManagerFactory().getObject() );
//    return txManager;
//  }
}