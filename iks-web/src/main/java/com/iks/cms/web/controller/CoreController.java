package com.iks.cms.web.controller;

import com.iks.cms.web.service.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping( "/core" )
public class CoreController {
  private final Logger logger = LoggerFactory.getLogger( CoreController.class );
  @Autowired
  private GridService gridService;
  @RequestMapping( value = "/getGrid", method = RequestMethod.GET )
  public Object index( String name ) {
    return gridService.getGrid( name );
  }
}