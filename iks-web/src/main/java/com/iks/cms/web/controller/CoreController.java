package com.iks.cms.web.controller;

import com.iks.cms.core.exception.*;
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
  private static final String REQUEST_GET_GRID_DATA                  = "/getGridData";
  private static final String RESPONSE_ERROR_GET_GRID_DATA           = "Couldn't get grid data";
  private static final String REQUEST_GET_EDIT_DATA                  = "/getEditData";
  private static final String RESPONSE_ERROR_GET_EDIT_DATA           = "Couldn't get edit data";
  private static final String REQUEST_UPDATE_EDIT_DATA               = "/updateEditData";
  private static final String REQUEST_ADD_GRID_ITEM                  = "/addGridItem";
  private static final String RESPONSE_ERROR_UPDATE_EDIT_DATA        = "Couldn't update edit data";
  private static final String REQUEST_DELETE_ITEM                    = "/deleteItem";
  private static final String RESPONSE_ERROR_DELETE_ITEM             = "Couldn't delete item";
  private static final String REQUEST_DELETE_ONE_TO_MANY_ITEM        = "/deleteOneToManyItem";
  private static final String RESPONSE_ERROR_DELETE_ONE_TO_MANY_ITEM = "Couldn't delete one to many item";
  private final        Logger logger                                 = LoggerFactory.getLogger( CoreController.class );
  @Autowired
  private AppObjService appObjService;
  @RequestMapping( value = REQUEST_GET_GRID_DATA, method = RequestMethod.POST )
  public ResponseEntity< DefaultResponseBody< ?, ? > > getGridData( @RequestBody RequestGetGridData request ) {
    try {
      ResponseGetGridData response = new ResponseGetGridData();
      response.setResult( appObjService.getGridData( request ) );
      return ApiUtils.makeResponse( REQUEST_GET_GRID_DATA, request, response );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_GET_GRID_DATA, ex );
      return ApiUtils.makeErrorResponse( REQUEST_GET_GRID_DATA, RESPONSE_ERROR_GET_GRID_DATA, request, ex );
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
      return ApiUtils.makeErrorResponse( REQUEST_GET_EDIT_DATA, RESPONSE_ERROR_GET_EDIT_DATA, request, ex );
    }
  }
  @RequestMapping( value = REQUEST_UPDATE_EDIT_DATA, method = RequestMethod.POST )
  public ResponseEntity< DefaultResponseBody< ?, ? > > updateEditData( @RequestBody RequestUpdateEditData request ) {
    try {
      if( request.isNew() ) {
        appObjService.saveNewItem( request.getAppObj(), request.getItem() );
      } else {
        appObjService.updateItem( request.getAppObj(), request.getItem() );
      }
      return ApiUtils.makeResponse( REQUEST_UPDATE_EDIT_DATA, request, new ResponseEmpty() );
    } catch( ValidationException ex ) {
      logger.error( RESPONSE_ERROR_UPDATE_EDIT_DATA, ex );
      ResponseValidationException response = new ResponseValidationException( ex );
      return ApiUtils.makeClientErrorResponse( REQUEST_UPDATE_EDIT_DATA, RESPONSE_ERROR_UPDATE_EDIT_DATA, response, request );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_UPDATE_EDIT_DATA, ex );
      return ApiUtils.makeErrorResponse( REQUEST_UPDATE_EDIT_DATA, RESPONSE_ERROR_UPDATE_EDIT_DATA, request, ex );
    }
  }
  @RequestMapping( value = REQUEST_ADD_GRID_ITEM, method = RequestMethod.POST )
  public ResponseEntity< DefaultResponseBody< ?, ? > > addGridItem( @RequestBody RequestAddGridItem request ) {
    try {
      appObjService.addReferenceGridItem( request.getGridId(), request.getParentItemId(), request.getItemId() );
      return ApiUtils.makeResponse( REQUEST_UPDATE_EDIT_DATA, request, new ResponseEmpty() );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_UPDATE_EDIT_DATA, ex );
      return ApiUtils.makeErrorResponse( REQUEST_UPDATE_EDIT_DATA, RESPONSE_ERROR_UPDATE_EDIT_DATA, request, ex );
    }
  }
  @RequestMapping( value = REQUEST_DELETE_ITEM, method = RequestMethod.POST )
  public ResponseEntity< DefaultResponseBody< ?, ? > > deleteItem( @RequestBody RequestDeleteItem request ) {
    try {
      appObjService.deleteItem( request.getGridId(), request.getItemId() );
      return ApiUtils.makeResponse( REQUEST_DELETE_ITEM, request, new ResponseEmpty() );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_DELETE_ITEM, ex );
      return ApiUtils.makeErrorResponse( REQUEST_DELETE_ITEM, RESPONSE_ERROR_DELETE_ITEM, request, ex );
    }
  }
  @RequestMapping( value = REQUEST_DELETE_ONE_TO_MANY_ITEM, method = RequestMethod.POST )
  public ResponseEntity< DefaultResponseBody< ?, ? > > deleteOneToManyItem( @RequestBody RequestDeleteManyToManyItem request ) {
    try {
      appObjService.deleteOneToManyItem( request.getGridId(), request.getParentItemId(), request.getItemId() );
      return ApiUtils.makeResponse( REQUEST_DELETE_ONE_TO_MANY_ITEM, request, new ResponseEmpty() );
    } catch( Exception ex ) {
      logger.error( RESPONSE_ERROR_DELETE_ITEM, ex );
      return ApiUtils.makeErrorResponse( REQUEST_DELETE_ONE_TO_MANY_ITEM, RESPONSE_ERROR_DELETE_ITEM, request, ex );
    }
  }
}