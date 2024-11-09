package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.enums.SortType;
import com.scaler.EcomProductService.model.Product;
import com.scaler.EcomProductService.model.SortParam;
import com.scaler.EcomProductService.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> searchAllProducts(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.toList();
    }

    public Page<Product> searchProductsByTitle(int pageNumber, int pageSize, String title, List<SortParam> sortParamList){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Sort sort = null;
        // System.out.println("Sort param list:"+sortParamList.toString());
        if(sortParamList!=null && !sortParamList.isEmpty()){
            Sort sortTemp = null;
            sortTemp = Sort.by(sortParamList.get(0).getParamName());
            if(sortParamList.get(0).getSortType()== SortType.DESC){
                sortTemp = sortTemp.descending();
            }
            else{
                sortTemp = sortTemp.ascending();
            }
            sort = Sort.unsorted().and(sortTemp);

            for(int i=1;i<sortParamList.size();i++){
                sortTemp = Sort.by(sortParamList.get(i).getParamName());
                if(sortParamList.get(i).getSortType()== SortType.DESC){
                    sortTemp = sortTemp.descending();
                }
                else{
                    sortTemp = sortTemp.ascending();
                }
                sort = sort.and(sortTemp);
            }
        }

        // System.out.println("Sort object:"+sort.toString());

        if(sort != null) {
            pageRequest = pageRequest.withSort(sort);
        }

        return productRepository.findByTitleContainsIgnoreCase(title, pageRequest);
    }
}
