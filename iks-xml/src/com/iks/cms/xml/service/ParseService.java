package com.iks.cms.xml.service;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.service.*;
import com.iks.cms.xml.parser.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import javax.annotation.*;

/**
 * @author Igor Kaynov
 */
@Service
public class ParseService {
  private static final Logger logger = LoggerFactory.getLogger( ParseService.class );
  @Autowired
  private AppObjService appObjService;
  @PostConstruct
  private void init() {
    loadApp();
    //    AppObj employeeAppObj = new AppObj();
    //    employeeAppObj.setName( "employee" );
    //    IDataModel model = parseDataModel( "resources/appObj/employee/data.xml" );
    //    IGrid grid = parseGrid( "resources/appObj/employee/list.xml" );
    //    employeeAppObj.setDataModel( model );
    //    employeeAppObj.setGrid( grid );
    //    appObjService.addAppObj( employeeAppObj );
  }
  public void loadApp() {
    appObjService.clear();
    parseApp( "resources/appObj/app.xml" );
  }
  private void parseApp( String fileName ) {
    AppParser parser = new AppParser();
    try {
      List< IAppObj > appObjList = parser.parse( fileName );
      appObjList.forEach( appObjService::addAppObj );
    } catch( Exception e ) {
      logger.error( "Data model parse exception: ", e );
    }
  }
  //  private IDataModel parseDataModel( String fileName ) {
  //    ModelParser parser = new ModelParser();
  //    try {
  //      return parser.parse( fileName );
  //    } catch( Exception e ) {
  //      logger.error( "Data model parse exception: ", e );
  //    }
  //    return null;
  //  }
  //  private IGrid parseGrid( String fileName ) {
  //    GridViewParser parser = new GridViewParser();
  //    try {
  //      return parser.parse( fileName );
  //    } catch( Exception e ) {
  //      logger.error( "Data model parse exception: ", e );
  //    }
  //    return null;
  //  }
}
