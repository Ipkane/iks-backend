package com.iks.cms.core.data;

import com.iks.cms.core.constant.*;

import org.apache.commons.lang3.*;
import org.w3c.dom.*;

import java.text.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Igor Kaynov
 */
public class InputDataField extends SimpleDataField {
  private boolean isPrimaryKey = false;
  private EDataType type = EDataType.STRING;

  public boolean isPrimaryKey() {
    return isPrimaryKey;
  }

  public void setIsPrimaryKey(boolean isPrimaryKey) {
    this.isPrimaryKey = isPrimaryKey;
  }

  @Override
  public Object parseValue(String value) {
    if (value == null) {
      return null;
    }
    switch (type) {
      case STRING:
        return value;
      case BOOLEAN:
        return BooleanUtils.toBoolean(value);
      case TIME:
        LocalTime dateTime = LocalTime.parse(value, DateTimeFormatter.ISO_TIME);
        return dateTime.format(DateTimeFormatter.ISO_TIME);
      default:
        return value;
    }
  }

  @Override
  public Object formatValue(Object value) {
    if (value == null) {
      return null;
    }
    switch (type) {
      case TIME:
        LocalTime dateTime = null;
        try {
          dateTime = LocalTime.parse((String) value, DateTimeFormatter.ISO_DATE_TIME);
        } catch (Exception e) {
          dateTime = LocalTime.parse((String) value, DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return dateTime.format(DateTimeFormatter.ISO_TIME);
      default:
        return value;
    }
  }

  public EDataType getType() {
    return type;
  }

  public void setType(EDataType type) {
    this.type = type;
  }

  @Override
  public void parse(Element xmlElement) {
    super.parse(xmlElement);
    if (xmlElement.hasAttribute(ModelConstant.PRIMARY_KEY_ATTR)) {
      setIsPrimaryKey(BooleanUtils.toBoolean(xmlElement.getAttribute(ModelConstant.PRIMARY_KEY_ATTR)));
    }
    if (xmlElement.hasAttribute(ModelConstant.TYPE_ATTR)) {
      setType(EDataType.valueOf(xmlElement.getAttribute(ModelConstant.TYPE_ATTR).toUpperCase()));
    }
  }
}
