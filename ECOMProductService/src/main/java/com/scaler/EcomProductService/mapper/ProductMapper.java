package com.scaler.EcomProductService.mapper;

import com.scaler.EcomProductService.dto.*;
import com.scaler.EcomProductService.model.Product;

import java.util.List;

public class ProductMapper {
    public static FakeStoreProductRequestDTO productRequestToFakeStoreProductRequest(ProductRequestDTO productRequestDTO){
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
        fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreProductRequestDTO.setImage(productRequestDTO.getImage());
        fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
        return fakeStoreProductRequestDTO;
    }

    public static ProductResponseDTO fakeProductResponseToProductResponse(FakeStoreProductResponseDTO fakeStoreProductResponseDTO){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        // this is done only to make the dto types compatible
        productResponseDTO.setId(fakeStoreProductResponseDTO.getUUID());
        productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
        productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());
        productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
        productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());
        productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());
        return productResponseDTO;
    }

    public static ProductListResponseDTO convertProductsToProductListResponseDTO(List<Product> products){
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for(Product p : products){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(p.getId());
            productResponseDTO.setImage(p.getImage());
            productResponseDTO.setTitle(p.getTitle());
            productResponseDTO.setPrice(p.getPrice().getAmount());
            productResponseDTO.setDescription(p.getDescription());
            productResponseDTO.setCategory(p.getCategory().getCategoryName());
            productListResponseDTO.getProducts().add(productResponseDTO);
        }
        return productListResponseDTO;
    }

    public static ProductResponseDTO convertProductToProductResponseDTO(Product p){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setCategory(p.getCategory().getCategoryName());
        productResponseDTO.setId(p.getId());
        productResponseDTO.setDescription(p.getDescription());
        productResponseDTO.setPrice(p.getPrice().getAmount());
        productResponseDTO.setTitle(p.getTitle());
        productResponseDTO.setImage(p.getImage());

        return productResponseDTO;
    }
}
