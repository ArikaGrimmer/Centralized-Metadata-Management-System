package com.test.evermeta.dto;

import jakarta.validation.constraints.*;

public class SearchRequest {
  @NotBlank(message = "keyword must not be blank")
  private String keyword;
  @Min(0)
  private Integer page = 0;
  @Min(1) @Max(100)
  private Integer size = 20;


  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }


}
