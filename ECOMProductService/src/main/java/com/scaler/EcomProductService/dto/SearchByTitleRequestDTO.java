package com.scaler.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchByTitleRequestDTO extends SearchRequestDTO{
    String title;
}
