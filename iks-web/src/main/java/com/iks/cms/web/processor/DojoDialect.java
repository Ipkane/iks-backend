package com.iks.cms.web.processor;

import org.thymeleaf.dialect.*;
import org.thymeleaf.processor.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class DojoDialect extends AbstractDialect {
  @Override
  public String getPrefix() {
    return "dojo";
  }
  @Override
  public Set< IProcessor > getProcessors() {
    final Set< IProcessor > processors = new HashSet<>();
    processors.add( new CommonDojoAttrProcessor( "args" ) );
    processors.add( new CommonDojoAttrProcessor( "props" ) );
    return processors;
  }
}
