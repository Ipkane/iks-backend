package com.iks.cms.web.api.common;

import com.fasterxml.jackson.annotation.*;

public class ViewEntityWrapper<T> {
  private final T                                  entity;
  @JsonIgnore
  private final Class<? extends JsonViews.Normal> view;
  public ViewEntityWrapper( T entity, Class< ? extends JsonViews.Normal > view ) {
    this.entity = entity;
    this.view = view;
  }
  @JsonValue
  public T getEntity() {
    return entity;
  }
  public Class<? extends JsonViews.Normal> getView() {
    return view;
  }
  public boolean hasView() {
    return view != null;
  }
}
