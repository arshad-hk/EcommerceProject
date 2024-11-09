package com.scaler.EcomProductService.dto;

import com.scaler.EcomProductService.model.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {
    int pageNumber;
    int pageSize;
    List<SortParam> sortParamList;
}
