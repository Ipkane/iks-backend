package com.iks.cms.core.service;

import com.iks.cms.core.appObj.*;
import com.iks.cms.core.data.*;
import com.iks.cms.core.exception.*;
import com.iks.cms.core.grid.*;
import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;
import com.iks.cms.core.model.*;
import com.iks.cms.core.query.editView.*;
import com.iks.cms.core.query.grid.*;
import com.iks.cms.core.query.model.*;
import com.iks.cms.core.repository.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import javax.annotation.*;

/**
 * @author Igor Kaynov
 */
@Service
public class AppObjService {
  private static final Logger logger = LoggerFactory.getLogger( AppObjService.class );
  @Autowired
  private CommonDao commonDao;
  @PostConstruct
  private void init() {
    App.getInstance().setService( this );
  }
  public PageableResult getGridData( IGridRequest request ) {
    IBaseGrid grid = App.getGrid( request.getGridId() );
    SelectGridQuery query = new SelectGridQuery( App.getModel( grid.getAppObj() ), grid );
    if( StringUtils.isNotBlank( request.getParentId() ) ) {
      query.setParentId( request.getParentId() );
    }
    if( request.getFilter() != null ) {
      for( Map.Entry< String, Object > entry : request.getFilter().entrySet() ) {
        query.addFilter( entry.getKey(), entry.getValue() );
      }
    }
    String orderBy = request.getOrderBy();
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
    if( request.getLimit() != null && request.getPage() != null ) {
      query.setOffset( ( request.getPage() - 1 ) * request.getLimit() );
      query.setLimit( request.getLimit() );
    }
    return query.getPageableResult( commonDao.getSessionFactory() );
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
      if( StringUtils.isNotBlank( field.getFieldName() ) ) {
        dataItem.addFieldValue( field.getFieldName(), field.getDefaultValue() );
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
  public void deleteItem( String gridId, Long itemId ) {
    IBaseGrid grid = App.getGrid( gridId );
    DeleteGridQuery query = new DeleteGridQuery( App.getModel( grid.getAppObj() ), grid, itemId );
    query.executeQuery( commonDao.getSessionFactory() );
  }
  public void addReferenceGridItem( String gridId, String parentItemId, String itemId ) {
    ReferenceTableField grid = (ReferenceTableField)App.getGrid( gridId );
    AddReferenceValueQuery query = new AddReferenceValueQuery( App.getModel( grid.getAppObj() ), grid.getFieldName(), parentItemId, itemId );
    query.execute( commonDao.getSessionFactory() );
  }
  public IListView getListView( String appObj ) {
    return App.getAppObj( appObj ).getListView();
  }
  public Map< String, List< SelectOption > > getEdiViewOptionsMap( String appObj ) {
    IEditView editView = App.getEditView( appObj );
    return getOptionsMap( appObj, editView.getFields() );
  }
  public Map< String, List< SelectOption > > getOptionsMap( String appObj, List< IGulInputField > fields ) {
    IDataModel model = App.getModel( appObj );
    Map< String, List< SelectOption > > optionsMap = new HashMap<>();
    for( IGulInputField field : fields ) {
      if( Objects.equals( field.getTag(), GulConstant.REFERENCE_SELECT ) ) {
        GulReferenceField referenceField = ( GulReferenceField )field;
        optionsMap.put( field.getFieldName(), getSelectOptions( model, referenceField ) );
      }
    }
    return optionsMap;
  }
  public List< SelectOption > getSelectOptions( IDataModel model, GulReferenceField referenceField ) {
    ManyToOne dataField = ( ManyToOne )model.getField( referenceField.getFieldName() );
    IDataModel joinedModel = App.getModel( dataField.getAppObj() );
    List< String > referencedFields = Arrays.asList( joinedModel.getPrimaryFieldName(), referenceField.getDisplayField() );
    SelectModelQuery query = new SelectModelQuery( App.getModel( dataField.getAppObj() ) );
    query.setFields( referencedFields );
    List< IDataItem > items = query.executeQuery( commonDao.getSessionFactory() );
    List< SelectOption > options = new ArrayList<>( items.size() );
    for( IDataItem item : items ) {
      options.add( new SelectOption( item.getFieldValue( joinedModel.getPrimaryFieldName() ).toString(), item.getFieldValue( referenceField.getDisplayField() ).toString() ) );
    }
    return options;
  }
}
