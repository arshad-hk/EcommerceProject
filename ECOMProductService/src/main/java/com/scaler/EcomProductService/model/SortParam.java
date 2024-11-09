package com.scaler.EcomProductService.model;

import com.scaler.EcomProductService.enums.SortType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    String paramName;
    SortType sortType;

    @Override
    public String
    toString() {
        return "SortParam{" +
                "paramName='" + paramName + '\'' +
                ", sortType=" + sortType +
                '}';
    }
}
