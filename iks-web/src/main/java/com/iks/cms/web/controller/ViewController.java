package com.iks.cms.web.controller;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.iks.cms.core.appObj.*;
import com.iks.cms.core.service.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping( "/view" )
public class ViewController {
  private static final String REQUEST_GET_LIST_VIEW = "/listView";
  private static final String REQUEST_GET_EDIT_VIEW = "/editView";
  private final        Logger logger                = LoggerFactory.getLogger( ViewController.class );
  @Autowired
  private AppObjService appObjService;
  @RequestMapping( value = REQUEST_GET_LIST_VIEW, method = RequestMethod.GET )
  public String listView( Model model, String appObj ) {
    IListView listView = appObjService.getListView( appObj );
    model.addAttribute( "listView", listView );
    model.addAttribute( "appObj", App.getAppObj( appObj ) );
    return listView.getTemplatePath();
  }
  @RequestMapping( value = REQUEST_GET_EDIT_VIEW, method = RequestMethod.GET )
  public String editView( Model model, String appObj, Long itemId ) {
    IEditView editView = App.getEditView( appObj );
    model.addAttribute( "editView", editView );
    model.addAttribute( "optionsMap", appObjService.getEdiViewOptionsMap( appObj ) );
    model.addAttribute( "appObj", App.getAppObj( appObj ) );
    return editView.getTemplatePath();
  }
}