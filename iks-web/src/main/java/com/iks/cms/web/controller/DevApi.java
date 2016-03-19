package com.iks.cms.web.controller;

import com.iks.cms.xml.service.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Igor Kaynov
 */
@RestController
@RequestMapping( "/dev" )
public class DevApi {
  @Autowired
  private ParseService parseService;

  @RequestMapping( value = "/reloadAppObj", method = RequestMethod.POST )
  public void reloadAppObj() {
parseService.loadApp();
  }
}
