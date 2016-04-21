package com.iks.cms.web.controller

import com.iks.cms.core.appObj.App
import com.iks.cms.core.appObj.IAppObj
import com.iks.cms.core.exception.*
import com.iks.cms.core.service.*
import com.iks.cms.web.api.common.*
import com.iks.cms.web.api.grid.*
import com.iks.cms.web.utils.*

import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

import java.util.ArrayList

@RestController
@RequestMapping("/api/core")
class CoreController {
  private val logger = LoggerFactory.getLogger(CoreController::class.java)
  @Autowired
  lateinit var appObjService: AppObjService

  @RequestMapping(value = REQUEST_GET_GRID_DATA, method = arrayOf(RequestMethod.POST))
  fun getGridData(@RequestBody request: RequestGetGridData): ResponseEntity<DefaultResponseBody<*, *>> {
    val response = ResponseGetGridData()
    response.result = appObjService.getGridData(request)
    return ApiUtils.makeResponse(response)
  }

  @RequestMapping(value = REQUEST_GET_EDIT_DATA, method = arrayOf(RequestMethod.GET))
  fun getEditData(request: RequestGetEditData): ResponseEntity<DefaultResponseBody<*, *>> {
    val response = ResponseGetEditData()
    response.item = appObjService.getEditData(request.appObj, request.itemId)
    return ApiUtils.makeResponse(response)
  }

  @RequestMapping(value = REQUEST_UPDATE_EDIT_DATA, method = arrayOf(RequestMethod.POST))
  fun updateEditData(@RequestBody request: RequestUpdateEditData): ResponseEntity<DefaultResponseBody<*, *>> {
    try {
      if (request.itemId == null) {
        throw IllegalArgumentException("Item id is null")
      } else if (request.itemId <= 0) {
        appObjService.saveNewItem(request.appObj, request.item)
      } else {
        appObjService.updateItem(request.appObj, request.itemId, request.item)
      }
      return ApiUtils.makeResponse(ResponseEmpty())
    } catch (ex: ValidationException) {
      logger.error(RESPONSE_ERROR_UPDATE_EDIT_DATA, ex)
      val response = ResponseValidationException(ex)
      return ApiUtils.makeClientErrorResponse(RESPONSE_ERROR_UPDATE_EDIT_DATA, response)
    }
  }

  @RequestMapping(value = REQUEST_ADD_GRID_ITEM, method = arrayOf(RequestMethod.POST))
  fun addGridItem(@RequestBody request: RequestAddGridItem): ResponseEntity<DefaultResponseBody<*, *>> {
    appObjService.addReferenceGridItem(request.gridId, request.parentItemId, request.itemId)
    return ApiUtils.makeResponse(ResponseEmpty())
  }

  @RequestMapping(value = REQUEST_DELETE_ITEM, method = arrayOf(RequestMethod.POST))
  fun deleteItem(@RequestBody request: RequestDeleteItem): ResponseEntity<DefaultResponseBody<*, *>> {
    appObjService.deleteItem(request.gridId, request.itemId)
    return ApiUtils.makeResponse(ResponseEmpty())
  }

  @RequestMapping(value = REQUEST_DELETE_ONE_TO_MANY_ITEM, method = arrayOf(RequestMethod.POST))
  fun deleteOneToManyItem(@RequestBody request: RequestDeleteManyToManyItem): ResponseEntity<DefaultResponseBody<*, *>> {
    appObjService.deleteOneToManyItem(request.gridId, request.parentItemId, request.itemId)
    return ApiUtils.makeResponse(ResponseEmpty())
  }

  @RequestMapping(value = REQUEST_GET_NAV, method = arrayOf(RequestMethod.GET))
  fun getNav(): ResponseEntity<DefaultResponseBody<*, *>> {
    val appObjList = ArrayList<String>()
    for (appObj in App.getAppObjList()) {
      appObjList.add(appObj.name)
    }
    return ApiUtils.makeResponse(ResponseNav(appObjList))
  }

  companion object {
    //  private static final String REQUEST_GET_GRID                = "/getGrid";
    //  private static final String RESPONSE_ERROR_GET_GRID         = "Couldn't get grid";
    private const val REQUEST_GET_GRID_DATA = "/getGridData"
    private const val REQUEST_GET_EDIT_DATA = "/getEditData"
    private const val REQUEST_UPDATE_EDIT_DATA = "/updateEditData"
    private const val REQUEST_ADD_GRID_ITEM = "/addGridItem"
    private const val RESPONSE_ERROR_UPDATE_EDIT_DATA = "Couldn't update edit data"
    private const val REQUEST_DELETE_ITEM = "/deleteItem"
    private const val REQUEST_DELETE_ONE_TO_MANY_ITEM = "/deleteOneToManyItem"
    private const val REQUEST_GET_NAV = "/getNav"
  }
}