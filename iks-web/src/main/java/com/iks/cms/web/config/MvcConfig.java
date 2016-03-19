package com.iks.cms.web.config;

import com.fasterxml.jackson.databind.*;

import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;

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
  @Override
  public void addResourceHandlers( ResourceHandlerRegistry registry ) {
    registry.addResourceHandler( "/assets/**" ).addResourceLocations( "/assets/" );
  }
  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass( JstlView.class );
    viewResolver.setPrefix( "/WEB-INF/views/jsp/" );
    viewResolver.setSuffix( ".jsp" );
    return viewResolver;
  }
  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter( mapper );
    return converter;
  }
}