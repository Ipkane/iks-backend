package com.iks.cms.core.common;

/**
 * @author Igor Kaynov
 */
public class NavItem {
  private String id;
  private String name;
  private String parent;
  private String url;
  private boolean directory = false;
  public NavItem(String id, String name) {
    this.id = id;
    this.name = name;
  }
  public String getId() {
    return id;
  }
  public void setId( String id ) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
  public String getParent() {
    return parent;
  }
  public void setParent( String parent ) {
    this.parent = parent;
  }
  public boolean isDirectory() {
    return directory;
  }
  public void setDirectory( boolean directory ) {
    this.directory = directory;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl( String url ) {
    this.url = url;
  }
}
