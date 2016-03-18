package com.iks.cms.web.webconfig;

import com.iks.cms.web.config.*;

import org.springframework.web.servlet.support.*;

//public class WebInitializer implements WebApplicationInitializer {
//  @Override
//  public void onStartup( ServletContext servletContext ) throws ServletException {
//    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//    ctx.register( BasicConfig.class, MvcConfig.class );
//    servletContext.addListener( new ContextLoaderListener( ctx ) );
//    AnnotationConfigWebApplicationContext dispatchCtx = new AnnotationConfigWebApplicationContext();
//    ServletRegistration.Dynamic dispatcher;
//    dispatcher = servletContext.addServlet( "dispatcher", new DispatcherServlet( dispatchCtx ) );
////    dispatcher.setLoadOnStartup( 1 );
//    dispatcher.addMapping( "/" );
//  }
//}
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { BasicConfig.class };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { MvcConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}