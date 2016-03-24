package com.iks.cms.web.processor;

import org.thymeleaf.dialect.*;
import org.thymeleaf.processor.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public class AngularDialect extends AbstractDialect {
  @Override
  public String getPrefix() {
    return "ng";
  }
  @Override
  public Set< IProcessor > getProcessors() {
    final Set< IProcessor > processors = new HashSet<>();
    processors.add( new CommonNgAttrProcessor("sref", "ui-sref") );
    processors.add( new CommonNgAttrProcessor("init") );
    processors.add( new CommonNgAttrProcessor("click") );
    processors.add( new CommonNgAttrProcessor("model") );
    processors.add( new CommonNgAttrProcessor("readonly") );
    return processors;
  }
}
