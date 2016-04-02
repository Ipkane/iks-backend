package com.iks.cms.web.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = {"com.iks.cms.web.service", "com.iks.cms.web.controller"})
public class MvcConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {
  //  @Bean( name = "templateResolver" )
//  public ITemplateResolver templateResolver() {
//    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//    templateResolver.setPrefix( "classpath:/templates/" );
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
  @Bean
  public ViewResolver viewResolver() {
    FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
    viewResolver.setCache(false);
    viewResolver.setPrefix("classpath:/templates/");
    viewResolver.setSuffix(".ftl");
    return viewResolver;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
  }

  @Bean
  public HttpMessageConverter httpMessageConverter() {
    ObjectMapper mapper = new ObjectMapper();
//    mapper.setSerializationInclusion( JsonInclude.Include.NON_NULL);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//    mapper.disable( DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES );
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
    return converter;
  }
}