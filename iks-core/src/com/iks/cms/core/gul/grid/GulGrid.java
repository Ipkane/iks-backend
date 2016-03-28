package com.iks.cms.core.gul.grid;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.container.*;
import com.iks.cms.core.gul.element.*;
import com.iks.cms.core.parser.*;

import org.w3c.dom.*;

import java.util.*;
import java.util.stream.*;

/**
 * @author Igor Kaynov
 */
public class GulGrid extends GulContainer {
  private List< GulRow >    rows    = new ArrayList<>();
  private List< GulColumn > columns = new ArrayList<>();
  @Override
  public String getTag() {
    return GulConstant.GRID;
  }
  @Override
  public String getTemplatePath() {
    return "gul/grid";
  }
  @Override
  public void parse( IParseContext context, Element xmlElement ) throws Exception {
    super.parse( context, xmlElement );
    for( IGulElement element : elements ) {
      if( element instanceof GulColumns ) {
        GulColumns columns = ( GulColumns )element;
        this.columns.addAll( columns.getElements().stream().filter( childElement -> childElement instanceof GulColumn ).map( childElement -> ( GulColumn )childElement ).collect( Collectors.toList() ) );
      } else if( element instanceof GulRows ) {
        GulRows rows = ( GulRows )element;
        this.rows.addAll( rows.getElements().stream().filter( childElement -> childElement instanceof GulRow ).map( childElement -> ( GulRow )childElement ).collect( Collectors.toList() ) );
      }
    }
  }
  public List< GulRow > getRows() {
    return rows;
  }
  public void setRows( List< GulRow > rows ) {
    this.rows = rows;
  }
  public List< GulColumn > getColumns() {
    return columns;
  }
  public void setColumns( List< GulColumn > columns ) {
    this.columns = columns;
  }
}
