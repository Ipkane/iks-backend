package com.iks.cms.web.config;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.iks.cms.web.processor.*;

import org.springframework.boot.autoconfigure.web.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;
import org.thymeleaf.*;
import org.thymeleaf.spring4.*;
import org.thymeleaf.spring4.templateresolver.*;
import org.thymeleaf.spring4.view.*;
import org.thymeleaf.templateresolver.*;

@Configuration
//@EnableWebMvc
@ComponentScan( basePackages = { "com.iks.cms.web.service", "com.iks.cms.web.controller" } )
public class MvcConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {
  @Bean( name = "templateResolver" )
  public ITemplateResolver templateResolver() {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setPrefix( "classpath:/templates/" );
    templateResolver.setSuffix( ".html" );
    templateResolver.setTemplateMode( "HTML5" );
    templateResolver.setCacheable( false );
    return templateResolver;
  }
  @Bean( name = "templateEngine" )
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setTemplateResolver( templateResolver() );
//    engine.addDialect( new AngularDialect() );
    engine.addDialect( new DojoDialect() );
    return engine;
  }
  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine( templateEngine() );
    return viewResolver;
  }
  @Override
  public void addResourceHandlers( ResourceHandlerRegistry registry ) {
    registry.addResourceHandler( "/assets/**" ).addResourceLocations( "classpath:/static/assets/" );
  }
  @Bean
  public HttpMessageConverter httpMessageConverter() {
    ObjectMapper mapper = new ObjectMapper();
//    mapper.setSerializationInclusion( JsonInclude.Include.NON_NULL);
    mapper.configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );
    mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    mapper.disable( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES );
//    mapper.disable( DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES );
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter( mapper );
    return converter;
  }
}