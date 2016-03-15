package com.iks.cms.web.grids;

import com.iks.cms.core.grid.*;

/**
 * @author Igor Kaynov
 */
public class EmployeeGrid extends Grid {
  public EmployeeGrid() {
    addField(new GridField( "id", "Id" )  );
    addField(new GridField( "firstName", "First Name" )  );
    addField(new GridField( "lastName", "Last Name" )  );
  }
}
