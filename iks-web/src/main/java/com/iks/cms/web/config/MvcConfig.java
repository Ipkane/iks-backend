package com.iks.cms.web.config;

import com.fasterxml.jackson.databind.*;
import com.iks.cms.web.processor.*;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;
import org.thymeleaf.*;
import org.thymeleaf.spring4.*;
import org.thymeleaf.spring4.view.*;
import org.thymeleaf.templateresolver.*;

@Configuration
@EnableWebMvc
@ComponentScan( basePackages = { "com.iks.cms.web.service", "com.iks.cms.web.controller" } )
public class MvcConfig extends WebMvcConfigurerAdapter {
  //  @Override
  //  public void addViewControllers( final ViewControllerRegistry registry ) {
  //    registry.addViewController( "/index.htm" ).setViewName( "/view/index.jsp" );
  //  }
  //  @Override
  //  public void configureDefaultServletHandling( final DefaultServletHandlerConfigurer configurer ) {
  //    configurer.enable();
  //  }
//  @Bean( name = "templateResolver" )
//  public ITemplateResolver templateResolver() {
//    ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
////    templateResolver.setPrefix( "resources/templates/" );
//    templateResolver.setSuffix( ".html" );
//    templateResolver.setTemplateMode( "HTML5" );
//    templateResolver.setCacheable( false );
//    return templateResolver;
//  }
//  @Bean( name = "templateEngine" )
//  public SpringTemplateEngine templateEngine() {
//    SpringTemplateEngine engine = new SpringTemplateEngine();
//    engine.setTemplateResolver( templateResolver() );
//    engine.addDialect( new AngularDialect() );
//    return engine;
//  }
//  @Bean
//  public ViewResolver viewResolver() {
//    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//    viewResolver.setTemplateEngine( templateEngine() );
//    return viewResolver;
//  }
  @Override
  public void addResourceHandlers( ResourceHandlerRegistry registry ) {
    registry.addResourceHandler( "/assets/**" ).addResourceLocations( "/assets/" );
  }
  //  @Bean
  //  public InternalResourceViewResolver viewResolver() {
  //    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
  //    viewResolver.setViewClass( JstlView.class );
  //    viewResolver.setPrefix( "/WEB-INF/views/jsp/" );
  //    viewResolver.setSuffix( ".jsp" );
  //    return viewResolver;
  //  }
  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter( mapper );
    return converter;
  }
}