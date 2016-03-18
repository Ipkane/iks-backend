package com.iks.cms.web.controller;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.web.api.common.*;
import com.iks.cms.web.api.grid.*;
import com.iks.cms.web.service.*;
import com.iks.cms.web.utils.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping( "/view" )
public class ViewController {
  private static final String REQUEST_GET_GRID_VIEW = "/gridView";
  private static final String REQUEST_GET_GRID_EDIT_VIEW = "/gridEditView";
  private final        Logger logger                = LoggerFactory.getLogger( ViewController.class );
  @Autowired
  private GridService gridService;
  @RequestMapping( value = REQUEST_GET_GRID_VIEW, method = RequestMethod.GET )
  public String gridView( Model model, String gridName ) {
    ObjectMapper objectMapper = new ObjectMapper();
    IGrid grid = gridService.getGrid( gridName );
    model.addAttribute( "gridName", gridName );
    model.addAttribute( "grid", grid );
    try {
      model.addAttribute( "gridJson", objectMapper.writeValueAsString( grid ).replace( "\"", "\\\"" ) );
    } catch( JsonProcessingException e ) {
      logger.error( "Serialization error:", e );
    }
    return "gridView";
  }
  @RequestMapping( value = REQUEST_GET_GRID_EDIT_VIEW, method = RequestMethod.GET )
  public String gridEditView( Model model, String gridName, Long itemId ) {
    IGrid grid = gridService.getGrid( gridName );
    model.addAttribute( "gridName", gridName );
    model.addAttribute( "grid", grid );
    return "gridEditView";
  }
}