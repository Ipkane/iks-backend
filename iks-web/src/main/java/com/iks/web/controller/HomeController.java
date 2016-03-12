package com.iks.web.controller;

import com.iks.web.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class HomeController {
  private final Logger logger = LoggerFactory.getLogger( HomeController.class );
  @Autowired
  private HomeService homeService;
  @RequestMapping( value = "/", method = RequestMethod.GET )
  public String index( Map< String, Object > model ) {
    logger.debug( "index() is executed!" );
    model.put( "title", homeService.getTitle( "" ) );
    model.put( "msg", homeService.getDesc() );
    return "index";
  }
  @RequestMapping( value = "/hello/{name:.+}", method = RequestMethod.GET )
  public ModelAndView hello( @PathVariable( "name" ) String name ) {
    logger.debug( "hello() is executed - $name {}", name );
    ModelAndView model = new ModelAndView();
    model.setViewName( "index" );
    model.addObject( "title", homeService.getTitle( name ) );
    model.addObject( "msg", homeService.getDesc() );
    return model;
  }
}