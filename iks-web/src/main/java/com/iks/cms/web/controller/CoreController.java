package com.iks.cms.web.controller;

import com.iks.cms.web.api.common.*;
import com.iks.cms.web.api.grid.*;
import com.iks.cms.web.service.*;
import com.iks.cms.web.utils.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/core" )
public class CoreController {
  private static final String REQUEST_GET_GRID             = "/getGrid";
  private static final String RESPONSE_ERROR_GET_GRID      = "Couldn't get grid";
  private static final String REQUEST_GET_GRID_DATA        = "/getGridData";
  private static final String RESPONSE_ERROR_GET_GRID_DATA = "Couldn't get grid data";
  private final        Logger logger                       = LoggerFactory.getLogger( CoreController.class );
  @Autowired
  private GridService gridService;
  @RequestMapping( value = REQUEST_GET_GRID, method = RequestMethod.GET )
  public ResponseEntity< DefaultResponseBody< ?, ? > > getGrid( RequestGetGrid request ) {
    try {
      ResponseGetGrid response = new ResponseGetGrid();
      response.setGrid( gridService.getGrid( request.getGridName() ) );
      return ApiUtils.makeResponse( REQUEST_GET_GRID, request, response );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_GET_GRID, ex );
      return ApiUtils.makeErrorResponse( REQUEST_GET_GRID, RESPONSE_ERROR_GET_GRID, request );
    }
  }
  @RequestMapping( value = REQUEST_GET_GRID_DATA, method = RequestMethod.GET )
  public ResponseEntity< DefaultResponseBody< ?, ? > > getGridData( RequestGetGridData request ) {
    try {
      ResponseGetGridData response = new ResponseGetGridData();
      response.setItems( gridService.getGridData( request.getGridName() ) );
      return ApiUtils.makeResponse( REQUEST_GET_GRID_DATA, request, response );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_GET_GRID_DATA, ex );
      return ApiUtils.makeErrorResponse( REQUEST_GET_GRID_DATA, RESPONSE_ERROR_GET_GRID_DATA, request );
    }
  }
}