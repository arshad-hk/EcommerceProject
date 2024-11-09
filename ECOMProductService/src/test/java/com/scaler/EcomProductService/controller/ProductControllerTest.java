package com.scaler.EcomProductService.controller;

import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.exception.ProductNotFoundException;
import com.scaler.EcomProductService.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean(name="productService")
    ProductService productService;

    @Test
    void getAllProductsReturnEmptyListWhenNoProductsAvailable() throws Exception {
        //arrange
        Mockito.when(productService.getAllProducts()).thenReturn(new ProductListResponseDTO());
        //act
        mockMvc.perform(get("/products"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("{\"products\":[]}"));
    }

    @Test
    void getAllProductsReturnsProducts() throws Exception {
        ProductResponseDTO product1 = new ProductResponseDTO();
        product1.setId(UUID.fromString("feecadf2-e74c-4a06-9e32-2e6d757158b2"));
        product1.setTitle("Laptop");
        product1.setCategory("Electronics");
        product1.setDescription("Best laptop");
        product1.setPrice(1000);
        product1.setImage("someImageURL");

        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        productListResponseDTO.setProducts(List.of(product1));

        Mockito.when(productService.getAllProducts()).thenReturn(productListResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("{\"products\":[{\"id\":\"feecadf2-e74c-4a06-9e32-2e6d757158b2\",\"title\":\"Laptop\",\"price\":1000.0,\"category\":\"Electronics\",\"description\":\"Best laptop\",\"image\":\"someImageURL\"}]}"));
    }

    @Test
    void createProductSuccess() throws Exception {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setCategory("Electronics");
        productRequestDTO.setDescription("some product");
        productRequestDTO.setTitle("Iphone");
        productRequestDTO.setImage("www.google.com/iphone");
        productRequestDTO.setPrice(10000);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setImage("www.google.com/iphone");
        productResponseDTO.setTitle("Iphone");
        productResponseDTO.setCategory("Electronics");
        productResponseDTO.setPrice(10000);
        productResponseDTO.setDescription("some product");
        productResponseDTO.setId(UUID.randomUUID());

        ObjectMapper objectMapper = new ObjectMapper();
        String responseJson = objectMapper.writeValueAsString(productResponseDTO);
        String requestJson = objectMapper.writeValueAsString(productRequestDTO);

        Mockito.when(productService.createProduct(ArgumentMatchers.eq(productRequestDTO))).thenReturn(productResponseDTO);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(responseJson));
    }

    @Test
    void deleteByIdSuccess() throws Exception {
        Mockito.when(productService.deleteProduct(ArgumentMatchers.anyInt())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    void deleteByIdFailure() throws Exception {
        Mockito.when(productService.deleteProduct(1)).thenThrow(new ProductNotFoundException("Product id does not exist."));

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Product id does not exist.\",\"messageCode\":404}"));
    }

    @Test
    void findProductByIdFailure() throws Exception {
        Mockito.when(productService.getProductById(1)).thenThrow(new ProductNotFoundException("Product id does not exist."));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Product id does not exist.\",\"messageCode\":404}"));
    }

    @Test
    void findProductByIdSuccess() throws Exception {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setImage("some-image");
        productResponseDTO.setTitle("some-title");
        productResponseDTO.setCategory("some-category");
        productResponseDTO.setPrice(100);
        productResponseDTO.setId(UUID.randomUUID());

        Mockito.when(productService.getProductById(1)).thenReturn(productResponseDTO);
        String responseJson = new ObjectMapper().writeValueAsString(productResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(responseJson));
    }

}
