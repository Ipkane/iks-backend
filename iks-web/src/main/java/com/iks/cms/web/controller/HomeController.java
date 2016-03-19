package com.iks.cms.web.controller;

import com.iks.cms.core.service.*;
import com.iks.cms.web.service.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HomeController {
  private final Logger logger = LoggerFactory.getLogger( HomeController.class );
  @Autowired
  private HomeService homeService;
  @Autowired
  private AppObjService appObjService;
  @RequestMapping( value = "/", method = RequestMethod.GET )
  public String index( Model model ) {
    model.addAttribute( "appObjList", appObjService.getAppObjList() );
    return "index";
  }
}