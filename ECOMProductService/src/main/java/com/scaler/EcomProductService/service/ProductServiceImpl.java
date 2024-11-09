package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.exception.InvalidTitleException;
import com.scaler.EcomProductService.exception.ProductNotFoundException;
import com.scaler.EcomProductService.mapper.ProductMapper;
import com.scaler.EcomProductService.model.Product;
import com.scaler.EcomProductService.repository.ProductRepository;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    private RestTemplate restTemplate;

    ProductServiceImpl(ProductRepository productRepository, RestTemplate restTemplate){
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<Product> products = productRepository.findAll();

        System.out.println("Making a call to user service for testing service discovery");
        String userServiceURL = "http://userservice/users/1";
        String userServiceResponse = restTemplate.getForObject(userServiceURL, String.class);
        System.out.println("Got response from user service:"+userServiceResponse);

        return ProductMapper.convertProductsToProductListResponseDTO(products);
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public ProductResponseDTO updateProduct(int id, ProductRequestDTO updatedProduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException, InvalidTitleException {
        if(title==null || title.isEmpty()){
            throw new InvalidTitleException("Cannot find product for invalid title");
        }

        Product product = productRepository.findProductByTitle(title);
        if(product == null){
            throw new ProductNotFoundException("Could not find product with title:"+title);
        }

        ProductResponseDTO productResponseDTO = ProductMapper.convertProductToProductResponseDTO(product);
        return productResponseDTO;
    }
}
