package com.iks.cms.web.config;

/*
** config data source , entity manager here
*/

import org.springframework.context.annotation.*;

@Configuration
//@EnableJpaRepositories( basePackages = { "com.makble.springmvcstart.repository" } )
//@EnableTransactionManagement
@ComponentScan( basePackages = { "com.iks.cms.web.service", "com.iks.cms.web.repository", "com.iks.cms.web.controller" } )
public class BasicConfig {
//  @Autowired
//  private DataSource dataSource;
//  @Bean
//  public DataSource dataSource() {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName( "com.mysql.jdbc.Driver" );
//    dataSource.setUrl( "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8" );
//    dataSource.setUsername( "root" );
//    dataSource.setPassword( "rootadm" );
//    return dataSource;
//  }
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
//    properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect" );  // MySQL5InnoDBDialect   MySQLMyISAMDialect
//    properties.put( "hibernate.show_sql", "true" );
//    return properties;
//  }
//  @Bean
//  public PlatformTransactionManager transactionManager() {
//    JpaTransactionManager txManager = new JpaTransactionManager();
//    txManager.setEntityManagerFactory( entityManagerFactory().getObject() );
//    return txManager;
//  }
}