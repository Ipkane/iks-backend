package com.iks.cms.core.service;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.exception.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.*;
import com.iks.cms.core.repository.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
@Service
public class AppObjService {
  private static final Logger                 logger    = LoggerFactory.getLogger( AppObjService.class );
  @Autowired
  private CommonDao commonDao;
  public List< IDataItem > getGridData( String appObj, Map< String, Object > filter, String orderBy ) {
    SelectGridQuery query = new SelectGridQuery( App.getModel( appObj ), App.getGrid( appObj ) );
    if( filter != null ) {
      query.setFilters( filter );
    }
    if( orderBy != null ) {
      boolean orderAsc = true;
      String orderField = orderBy;
      if( orderBy.substring( 0, 1 ).equals( "-" ) ) {
        orderField = orderBy.substring( 1, orderBy.length() );
        orderAsc = false;
      }
      query.setOrderBy( orderField );
      query.setOrderAsc( orderAsc );
    }
    return query.executeQuery( commonDao.getSessionFactory() );
  }
  public List< IDataItem > getModelData( String appObj, List< String > fields ) {
    SelectModelQuery query = new SelectModelQuery( App.getModel( appObj ) );
    query.setFields( fields );
    return query.executeQuery( commonDao.getSessionFactory() );
  }
  public IDataItem getEditData( String appObj, Long itemId ) {
    if( itemId == null ) {
      return createNewItem( appObj, App.getEditView( appObj ) );
    } else {
      SelectEditViewQuery query = new SelectEditViewQuery( App.getModel( appObj ), App.getEditView( appObj ), itemId );
      return query.executeSingleQuery( commonDao.getSessionFactory() );
    }
  }
  public IDataItem createNewItem( String appObj, IEditView editView ) {
    DataItem dataItem = new DataItem();
    for( IGulInputField field : editView.getFields() ) {
      if (StringUtils.isNotBlank(field.getName())) {
        dataItem.addFieldValue( field.getName(), field.getDefaultValue() );
      }
    }
    return dataItem;
  }
  public void saveNewItem( String appObj, IDataItem item ) throws ValidationException {
    IDataModel model = App.getModel( appObj );
    if( !model.validate( item ) ) {
      throw new ValidationException( item.getErrors() );
    }
    CreateEditViewQuery query = new CreateEditViewQuery( App.getModel( appObj ), App.getEditView( appObj ), item );
    query.executeQuery( commonDao.getSessionFactory() );
  }
  public void updateItem( String appObj, IDataItem item ) throws ValidationException {
    IDataModel model = App.getModel( appObj );
    if( !model.validate( item ) ) {
      throw new ValidationException( item.getErrors() );
    }
    UpdateEditViewQuery query = new UpdateEditViewQuery( model, App.getEditView( appObj ), item );
    query.executeQuery( commonDao.getSessionFactory() );
  }
  public void deleteItem( String appObj, Long itemId ) {
    DeleteEditViewQuery query = new DeleteEditViewQuery( App.getModel( appObj ), App.getEditView( appObj ), itemId );
    query.executeQuery( commonDao.getSessionFactory() );
  }
  public IGridView getGridView( String appObj ) {
    return App.getAppObj( appObj ).getGridView();
  }
  public Map< String, List< SelectOption > > getEdiViewOptionsMap( String appObj ) {
    Map< String, List< SelectOption > > optionsMap = new HashMap<>();
    IDataModel model = App.getModel( appObj );
    IEditView editView = App.getEditView( appObj );
    for( IGulInputField field : editView.getFields() ) {
      if( Objects.equals( field.getTag(), GulConstant.REFERENCE_SELECT ) ) {
        GulReferenceField referenceField = ( GulReferenceField )field;
        ManyToOne dataField = ( ManyToOne )model.getField( field.getName() );
        List< String > referencedFields = Arrays.asList( dataField.getReferenceField(), referenceField.getDisplayField() );
        SelectModelQuery query = new SelectModelQuery( App.getModel( dataField.getAppObj() ) );
        query.setFields( referencedFields );
        List< IDataItem > items = query.executeQuery( commonDao.getSessionFactory() );
        List< SelectOption > options = new ArrayList<>( items.size() );
        for( IDataItem item : items ) {
          options.add( new SelectOption( item.getFieldValue( dataField.getReferenceField() ).toString(), item.getFieldValue( referenceField.getDisplayField() ).toString() ) );
        }
        optionsMap.put( field.getName(), options );
      }
    }
    return optionsMap;
  }
}
