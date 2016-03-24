package com.iks.cms.web.controller;

import com.iks.cms.web.api.common.*;
import com.iks.cms.web.api.grid.*;
import com.iks.cms.web.utils.*;
import com.iks.cms.xml.service.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Igor Kaynov
 */
@RestController
@RequestMapping( "/dev" )
public class DevApi {
  private final Logger logger = LoggerFactory.getLogger( DevApi.class );
  private static final String REQUEST_RELOAD_APP      = "/reloadAppObj";
  private static final String REQUEST_GET_GRID_EDIT_VIEW = "/gridEditView";
  @Autowired
  private ParseService parseService;
  @RequestMapping( value = REQUEST_RELOAD_APP, method = RequestMethod.POST )
  public ResponseEntity< DefaultResponseBody< ?, ? > > reloadAppObj() {
    try {
      parseService.loadApp();
      return ApiUtils.makeResponse( REQUEST_RELOAD_APP, null, new ResponseEmpty() );
    } catch( Exception ex ) {
      return ApiUtils.makeErrorResponse( REQUEST_RELOAD_APP, "Error reloading app", null );
    }
  }
}
