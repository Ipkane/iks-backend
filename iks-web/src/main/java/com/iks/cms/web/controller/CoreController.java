package com.iks.cms.web.controller;

import com.iks.cms.core.service.*;
import com.iks.cms.web.api.common.*;
import com.iks.cms.web.api.grid.*;
import com.iks.cms.web.utils.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/core" )
public class CoreController {
  //  private static final String REQUEST_GET_GRID                = "/getGrid";
  //  private static final String RESPONSE_ERROR_GET_GRID         = "Couldn't get grid";
  private static final String REQUEST_GET_GRID_DATA           = "/getGridData";
  private static final String RESPONSE_ERROR_GET_GRID_DATA    = "Couldn't get grid data";
  private static final String REQUEST_GET_EDIT_DATA           = "/getEditData";
  private static final String RESPONSE_ERROR_GET_EDIT_DATA    = "Couldn't get edit data";
  private static final String REQUEST_UPDATE_EDIT_DATA        = "/updateEditData";
  private static final String RESPONSE_ERROR_UPDATE_EDIT_DATA = "Couldn't update edit data";
  private final        Logger logger                          = LoggerFactory.getLogger( CoreController.class );
  @Autowired
  private AppObjService appObjService;
  //  @RequestMapping( value = REQUEST_GET_GRID, method = RequestMethod.GET )
  //  public ResponseEntity< DefaultResponseBody< ?, ? > > getGrid( RequestGetGrid request ) {
  //    try {
  //      ResponseGetGrid response = new ResponseGetGrid();
  //      response.setGrid( appObjService.getGrid( request.getAppObj() ) );
  //      return ApiUtils.makeResponse( REQUEST_GET_GRID, request, response );
  //    } catch( Exception ex ) {
  //      logger.error( RESPONSE_ERROR_GET_GRID, ex );
  //      return ApiUtils.makeErrorResponse( REQUEST_GET_GRID, RESPONSE_ERROR_GET_GRID, request );
  //    }
  //  }
  @RequestMapping( value = REQUEST_GET_GRID_DATA, method = RequestMethod.GET )
  public ResponseEntity< DefaultResponseBody< ?, ? > > getGridData( RequestGetGridData request ) {
    try {
      ResponseGetGridData response = new ResponseGetGridData();
      response.setItems( appObjService.getGridData( request.getAppObj() ) );
      return ApiUtils.makeResponse( REQUEST_GET_GRID_DATA, request, response );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_GET_GRID_DATA, ex );
      return ApiUtils.makeErrorResponse( REQUEST_GET_GRID_DATA, RESPONSE_ERROR_GET_GRID_DATA, request );
    }
  }
  @RequestMapping( value = REQUEST_GET_EDIT_DATA, method = RequestMethod.GET )
  public ResponseEntity< DefaultResponseBody< ?, ? > > getEditData( RequestGetEditData request ) {
    try {
      ResponseGetEditData response = new ResponseGetEditData();
      response.setItem( appObjService.getEditData( request.getAppObj(), request.getItemId() ) );
      return ApiUtils.makeResponse( REQUEST_GET_EDIT_DATA, request, response );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_GET_EDIT_DATA, ex );
      return ApiUtils.makeErrorResponse( REQUEST_GET_EDIT_DATA, RESPONSE_ERROR_GET_EDIT_DATA, request );
    }
  }
  @RequestMapping( value = REQUEST_UPDATE_EDIT_DATA, method = RequestMethod.POST )
  public ResponseEntity< DefaultResponseBody< ?, ? > > updateEditData( @RequestBody RequestUpdateEditData request ) {
    try {
      appObjService.updateEditData(request.getAppObj(), request.getItem());
      return ApiUtils.makeResponse( REQUEST_UPDATE_EDIT_DATA, request, new ResponseEmpty() );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_UPDATE_EDIT_DATA, ex );
      return ApiUtils.makeErrorResponse( REQUEST_UPDATE_EDIT_DATA, RESPONSE_ERROR_UPDATE_EDIT_DATA, request );
    }
  }
}