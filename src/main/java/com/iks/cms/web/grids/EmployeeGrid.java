package com.iks.cms.web.grids;

import com.iks.cms.core.grid.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class EmployeeGrid extends Grid {
  public EmployeeGrid() {
    addField(new GridField( "id", "Id" )  );
    addField(new GridField( "firstName", "First Name" )  );
    addField(new GridField( "lastName", "Last Name" )  );
  }
  public List<IGridDataRow> getTestData() {
    List<IGridDataRow> data = new ArrayList<>(  );
    GridDataRow row1 = new GridDataRow();
    row1.addData( "id", "1" );
    row1.addData( "firstName", "Mark" );
    row1.addData( "lastName", "Yolberg" );
    GridDataRow row2 = new GridDataRow();
    row2.addData( "id", "2" );
    row2.addData( "firstName", "Ivan" );
    row2.addData( "lastName", "Ivanov" );
    data.add( row1 );
    data.add( row2 );
    return data;
  }
}
