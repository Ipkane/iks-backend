package com.iks.cms.web.controller;

import com.iks.cms.web.service.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HomeController {
  private final Logger logger = LoggerFactory.getLogger( HomeController.class );
  @Autowired
  private HomeService homeService;
  @RequestMapping( value = "/", method = RequestMethod.GET )
  public String index( Map< String, Object > model ) {
    return "index";
  }
}