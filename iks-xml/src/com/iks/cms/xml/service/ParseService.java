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
    try {
      loadApp();
    } catch( Exception e ) {
    }
  }
  public void loadApp() throws Exception {
    try {
      appObjService.clear();
      parseApp( "resources/appObj/app.xml" );
    } catch( Exception e ) {
      logger.error( "Error loading app: ", e );
      throw e;
    }
  }
  private void parseApp( String fileName ) throws Exception {
    AppParser parser = new AppParser();
    List< IAppObj > appObjList = parser.parse( fileName );
    appObjList.forEach( appObjService::addAppObj );
  }
}
