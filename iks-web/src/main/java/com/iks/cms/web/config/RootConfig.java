package com.iks.cms.web.config;

/*
** config data source , entity manager here
*/

import com.fasterxml.jackson.databind.*;
import com.iks.cms.config.properties.*;
import com.iks.cms.xml.config.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.*;
import org.springframework.jdbc.datasource.*;
import org.springframework.orm.hibernate4.*;
import org.springframework.transaction.annotation.*;

import javax.annotation.*;
import javax.sql.*;

@Configuration
//@EnableJpaRepositories( basePackages = { "com.makble.springmvcstart.repository" } )
@EnableTransactionManagement
@ComponentScan( basePackages = { "com.iks.cms.core.service", "com.iks.cms.core.repository" } )
@EnableConfigurationProperties( { DbProperties.class } )
//@PropertySource( "classpath:application.properties" )
@Import( { XmlConfig.class } )
public class RootConfig {
  //  @Autowired
  //  private DataSource  dataSource;
  @Autowired
  private DbProperties dbProperties;
  @Resource
  public  Environment  env;
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    //    System.out.println(env.getProperty( "db.url" ));
    //    dataSource.setDriverClassName( env.getProperty( "db.driver" ));
    //    dataSource.setUrl( env.getProperty( "db.url" ) );
    //    dataSource.setUsername( env.getProperty( "db.username" ) );
    //    dataSource.setPassword( env.getProperty( "db.password" ) );
    dataSource.setDriverClassName( dbProperties.getDriver() );
    dataSource.setUrl( dbProperties.getUrl() );
    dataSource.setUsername( dbProperties.getUsername() );
    dataSource.setPassword( dbProperties.getPassword() );
    return dataSource;
  }
  @Bean
  public SessionFactory sessionFactory() {
    return new LocalSessionFactoryBuilder( dataSource() ).buildSessionFactory();
  }
  @Bean
  public HibernateTransactionManager hibTransMan() {
    return new HibernateTransactionManager( sessionFactory() );
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