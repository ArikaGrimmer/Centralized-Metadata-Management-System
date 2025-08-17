package com.test.evermeta.dto;

public class MetadataColumnResponse {
  private Long id;
  private String columnName;
  private String tableName;
  private String schemaName;
  private String domainName;
  private String subDomainName;
  private String systemAlias;
  private String shortDescription;
  private String longDescription;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getSchemaName() {
    return schemaName;
  }

  public void setSchemaName(String schemaName) {
    this.schemaName = schemaName;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  public String getSubDomainName() {
    return subDomainName;
  }

  public void setSubDomainName(String subDomainName) {
    this.subDomainName = subDomainName;
  }

  public String getSystemAlias() {
    return systemAlias;
  }

  public void setSystemAlias(String systemAlias) {
    this.systemAlias = systemAlias;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }


}
