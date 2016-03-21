package com.iks.cms.core.data;

import org.apache.commons.lang3.*;

/**
 * @author Igor Kaynov
 */
public class SimpleDataField extends DataField {
  private boolean isPrimaryKey = false;
  public boolean isPrimaryKey() {
    return isPrimaryKey;
  }
  public void setIsPrimaryKey( boolean isPrimaryKey ) {
    this.isPrimaryKey = isPrimaryKey;
  }
}
