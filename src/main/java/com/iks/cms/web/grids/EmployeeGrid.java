package com.iks.cms.web.grids;

import com.iks.cms.core.data.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.model.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class EmployeeGrid extends Grid {
  public EmployeeGrid() {
    BaseDataModel model = new BaseDataModel();
    model.setTableName( "employee" );
    DataField id = new DataField();
    //    id.setName( "id" );
    id.setTableField( "id" );
    //    id.setLabel( "Id" );
    DataField fio = new DataField();
    //    fio.setName( "fio" );
    fio.setTableField( "fio" );
    //    fio.setLabel( "First/Last name" );
    model.addField( id );
    model.addField( fio );
    for( IDataField modelField : model.getFields() ) {
      addField( new GridField( modelField.getName(), modelField.getLabel() ) );
    }
    this.dataModel = model;
  }
}
