package com.scaler.EcomProductService.controller;

import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.exception.InvalidTitleException;
import com.scaler.EcomProductService.exception.ProductNotFoundException;
import com.scaler.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService productService; // immutable

    @Autowired // Autowired for constructor injection is optional from Spring 4.x+ onwards
    public ProductController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    /**
    Field Injection
 //    @Autowired
 //    @Qualifier("fakeStoreProductService")
 //    private ProductService productService;
 */

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        System.out.println("Request reached to this product service instance.");
        ProductListResponseDTO response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getProductFromId(@PathVariable("id") int id) throws ProductNotFoundException {
        System.out.println("Request reached to this product service instance.");
        ProductResponseDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/title/{title}")
    public ResponseEntity findProductByTitle(@PathVariable("title") String title) throws ProductNotFoundException, InvalidTitleException {
        ProductResponseDTO productResponseDTO = productService.findProductByTitle(title);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        System.out.println("create product:"+productRequestDTO);
        ProductResponseDTO responseDTO = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") int id) throws ProductNotFoundException {
        boolean response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity updateProductById(ProductRequestDTO productRequestDTO, @PathVariable("id")int id){
        ProductResponseDTO productResponseDTO = productService.updateProduct(id, productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }
}
/*
    Domain Name -> IP+Port -> OS in server -> port is binded to process -> Tomcat is binded to 8080
    HTTP -> Tomcat -> DispatcherServlet[loads all URls and handler mappings] -> Servlet Container -> Servlets
 */
