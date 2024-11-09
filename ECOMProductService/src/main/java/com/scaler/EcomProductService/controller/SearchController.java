package com.scaler.EcomProductService.controller;

import com.scaler.EcomProductService.dto.SearchByTitleRequestDTO;
import com.scaler.EcomProductService.dto.SearchRequestDTO;
import com.scaler.EcomProductService.model.Product;
import com.scaler.EcomProductService.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @PostMapping("/")
    List<Product> getAllProductsPagination(@RequestBody SearchRequestDTO searchRequestDTO){
        return searchService.searchAllProducts(searchRequestDTO.getPageNumber(),
                searchRequestDTO.getPageSize());
    }

    @PostMapping("/title")
    Page<Product> getProductsByTitle(@RequestBody SearchByTitleRequestDTO searchRequestDTO){
        return searchService.searchProductsByTitle(searchRequestDTO.getPageNumber(),
                searchRequestDTO.getPageSize(), searchRequestDTO.getTitle(), searchRequestDTO.getSortParamList());
    }
}
