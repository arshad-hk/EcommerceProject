package com.scaler.EcomProductService.mapper;

import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.model.Category;
import com.scaler.EcomProductService.model.Price;
import com.scaler.EcomProductService.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProductMapperTest {

    @Test
    void testConvertProductToProductResponseDTO(){
        Price price = new Price();
        price.setDiscount(10);
        price.setCurrency("rupees");
        price.setAmount(100);
        price.setId(UUID.randomUUID());

        Category category = new Category();
        category.setCategoryName("some-category");
        category.setId(UUID.randomUUID());

        UUID productUUID = UUID.randomUUID();
        Product product = new Product();
        product.setImage("some-image");
        product.setTitle("some-title");
        product.setPrice(price);
        product.setCategory(category);
        product.setId(productUUID);
        product.setDescription("some-description");

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(productUUID);
        productResponseDTO.setDescription("some-description");
        productResponseDTO.setTitle("some-title");
        productResponseDTO.setImage("some-image");
        productResponseDTO.setPrice(100);
        productResponseDTO.setCategory("some-category");

        ProductResponseDTO actualResponseDTO = ProductMapper.convertProductToProductResponseDTO(product);

        Assertions.assertEquals(productResponseDTO, actualResponseDTO);

    }
}
