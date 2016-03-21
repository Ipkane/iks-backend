package com.iks.cms.web.controller;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.iks.cms.core.appObj.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.service.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping( "/view" )
public class ViewController {
  private static final String REQUEST_GET_GRID_VIEW      = "/gridView";
  private static final String REQUEST_GET_GRID_EDIT_VIEW = "/gridEditView";
  private final        Logger logger                     = LoggerFactory.getLogger( ViewController.class );
  @Autowired
  private AppObjService appObjService;
  @RequestMapping( value = REQUEST_GET_GRID_VIEW, method = RequestMethod.GET )
  public String gridView( Model model, String appObj ) {
    ObjectMapper objectMapper = new ObjectMapper();
    IGrid grid = appObjService.getGrid( appObj );
    model.addAttribute( "gridName", appObj );
    model.addAttribute( "grid", grid );
    try {
      model.addAttribute( "gridJson", objectMapper.writeValueAsString( grid ).replace( "\"", "\\\"" ) );
    } catch( JsonProcessingException e ) {
      logger.error( "Serialization error:", e );
    }
    return "gridView";
  }
  @RequestMapping( value = REQUEST_GET_GRID_EDIT_VIEW, method = RequestMethod.GET )
  public String gridEditView( Model model, String appObj, Long itemId ) {
    IEditView editView = appObjService.getEditView( appObj );
    model.addAttribute( "editView", editView );
    model.addAttribute( "optionsMap", appObjService.getEdiViewOptionsMap( appObj ) );
    model.addAttribute( "appObj", appObjService.getAppObj( appObj ) );
    return "editView";
  }
}