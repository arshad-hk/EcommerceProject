package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.client.FakeStoreAPIClient;
import com.scaler.EcomProductService.dto.*;
import com.scaler.EcomProductService.exception.ProductNotFoundException;
import com.scaler.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.scaler.EcomProductService.mapper.ProductMapper.fakeProductResponseToProductResponse;
import static com.scaler.EcomProductService.mapper.ProductMapper.productRequestToFakeStoreProductRequest;
import static com.scaler.EcomProductService.util.ProductUtils.isNull;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    private FakeStoreAPIClient fakeStoreAPIClient;
    private RedisTemplate<String, FakeStoreProductResponseDTO> redisTemplate;

    public FakeStoreProductServiceImpl(FakeStoreAPIClient fakeStoreAPIClient,
                                       RedisTemplate redisTemplate) {
        this.fakeStoreAPIClient = fakeStoreAPIClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOs = fakeStoreAPIClient.getAllProducts();
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for(FakeStoreProductResponseDTO fakeStoreProductResponseDTO : fakeStoreProductResponseDTOs){
            productListResponseDTO.getProducts().add(fakeProductResponseToProductResponse(fakeStoreProductResponseDTO));
        }
        return productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = null;
        fakeStoreProductResponseDTO = (FakeStoreProductResponseDTO) redisTemplate.opsForHash().get("PRODUCTS", id);
        if(fakeStoreProductResponseDTO == null){
            fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(id);
            if(isNull(fakeStoreProductResponseDTO)){
                throw new ProductNotFoundException("Product not found with id : " + id);
            }
            redisTemplate.opsForHash().put("PRODUCTS", id, fakeStoreProductResponseDTO);
        }

        return fakeProductResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = productRequestToFakeStoreProductRequest(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductDTO = fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);
        return fakeProductResponseToProductResponse(fakeStoreProductDTO);
    }

    @Override
    public boolean deleteProduct(int id) {
        fakeStoreAPIClient.deleteProduct(id);
        return true;
    }

    //TODO : complete this
    @Override
    public ProductResponseDTO updateProduct(int id, ProductRequestDTO updatedProduct) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = productRequestToFakeStoreProductRequest(updatedProduct);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.updateProduct(fakeStoreProductRequestDTO, id);
        return fakeProductResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) {
        return null;
    }
}
