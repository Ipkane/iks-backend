package com.iks.cms.core.service;

import net.jazdw.rql.parser.*;

import org.apache.commons.lang3.*;

/**
 * @author Igor Kaynov
 */
public class QueryRequestVisitor implements SimpleASTVisitor< QueryRequest > {
  private QueryRequest request;
  public QueryRequestVisitor() {
    request = new QueryRequest();
  }
  public QueryRequest getRequest() {
    return request;
  }
  @Override
  public QueryRequest visit( ASTNode node ) {
    switch( node.getName() ) {
      case "and":
        for( Object obj : node ) {
          if( obj instanceof ASTNode ) {
            ( ( ASTNode )obj ).accept( this );
          } else {
            throw new UnsupportedOperationException( "Encountered a non-ASTNode argument in AND statement" );
          }
        }
        return request;
      case "or":
        for( Object obj : node ) {
          if( obj instanceof ASTNode ) {
            ( ( ASTNode )obj ).accept( this );
          } else {
            throw new UnsupportedOperationException( "Encountered a non-ASTNode argument in AND statement" );
          }
        }
        return request;
      case "limit":
        request.setLimit( ( Integer )node.getArgument( 0 ) );
        return request;
      case "sort":
        request.setOrderBy( ( String )node.getArgument( 0 ) );
        return request;
      case "eq":
        String field = ( String )node.getArgument( 0 );
        if( field.equals( "gridId" ) ) {
          return request;
        }
        Object value = node.getArgument( 1 );
        value = value instanceof String ? StringUtils.trimToNull( ( String )value ) : value;
        request.getFilter().put( field, value );
      default:
        return request;
    }
  }
}
