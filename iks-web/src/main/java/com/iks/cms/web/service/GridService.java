package com.iks.cms.web.service;

import com.iks.cms.core.grid.*;
import com.iks.cms.web.grids.*;

import org.slf4j.*;
import org.springframework.stereotype.*;

import java.util.*;

import javax.annotation.*;

/**
 * @author Igor Kaynov
 */
@Service
public class GridService {
  private static final Logger               logger = LoggerFactory.getLogger( GridService.class );
  private              Map< String, IGrid > grids  = new HashMap<>();
  @PostConstruct
  public void init() {
    grids.put( "employee", new EmployeeGrid() );
  }
  public IGrid getGrid( String name ) {
    return grids.get( name );
  }
}
