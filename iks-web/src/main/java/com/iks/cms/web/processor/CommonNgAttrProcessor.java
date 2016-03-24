package com.iks.cms.web.processor;

import org.thymeleaf.*;
import org.thymeleaf.dom.*;
import org.thymeleaf.processor.*;
import org.thymeleaf.processor.attr.*;
import org.thymeleaf.standard.expression.*;

/**
 * @author Igor Kaynov
 */
public class CommonNgAttrProcessor extends AbstractAttrProcessor {
  private String attrName;
  public CommonNgAttrProcessor( String prefix ) {
    this( prefix, "ng-" + prefix );
  }
  public CommonNgAttrProcessor( String prefix, String attrName ) {
    super( prefix );
    this.attrName = attrName;
  }
  @Override
  public int getPrecedence() {
    return 12000;
  }
  @Override
  protected ProcessorResult processAttribute( Arguments arguments, Element element, String attributeName ) {
    final Configuration configuration = arguments.getConfiguration();
    final String attributeValue = element.getAttributeValue( attributeName );
    final IStandardExpressionParser parser = StandardExpressions.getExpressionParser( configuration );
    final IStandardExpression expression = parser.parseExpression( configuration, arguments, attributeValue );
    final String result = String.valueOf(expression.execute( configuration, arguments ));
    element.removeAttribute(attributeName);
    element.setAttribute( attrName, result );
    return ProcessorResult.OK;
  }
}
