package com.iks.cms.web.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import freemarker.cache.TemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.TemplateException;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;

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
    viewResolver.setPrefix("");
    viewResolver.setSuffix(".ftl");
    return viewResolver;
  }

  @Bean
  public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
    FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
    factory.setTemplateLoaderPaths("classpath:templates", "src/main/resources/templates");
    factory.setDefaultEncoding("UTF-8");
    FreeMarkerConfigurer result = new FreeMarkerConfigurer();
    result.setConfiguration(factory.createConfiguration());
    return result;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
    registry.addResourceHandler("/node_modules/**").addResourceLocations("classpath:/static/node_modules/");
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