package com.example.jwt.service.dto;

import com.example.jwt.enums.SearchType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDTO {

  @ApiModelProperty(name = "페이지 번호")
  private Integer page;

  @ApiModelProperty(name = "보여줄 숫자")
  private Integer size;

  @ApiModelProperty(name = "검색 값")
  private String searchValue;

  @ApiModelProperty(name = "검색 타입")
  private SearchType searchType;

  public Integer getPage() {
    if (null == this.page) return 0;
    return page;
  }

  public Integer getSize() {
    if (null == this.size) return 10;
    return size;
  }

  public SearchType getSearchType() {
    if (null == this.searchType) return SearchType.NONE;
    return this.searchType;
  }
}
