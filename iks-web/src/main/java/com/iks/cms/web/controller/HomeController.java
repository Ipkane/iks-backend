package com.iks.cms.web.controller;

import com.iks.cms.web.service.*;

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
    model.put( "title", homeService.getTitle( "" ) );
    model.put( "msg", homeService.getDesc() );
    return "index";
  }
}