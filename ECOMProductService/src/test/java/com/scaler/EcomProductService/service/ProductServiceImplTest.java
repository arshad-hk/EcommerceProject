package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.exception.InvalidTitleException;
import com.scaler.EcomProductService.exception.ProductNotFoundException;
import com.scaler.EcomProductService.model.Category;
import com.scaler.EcomProductService.model.Price;
import com.scaler.EcomProductService.model.Product;
import com.scaler.EcomProductService.repository.ProductRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;

import static org.mockito.ArgumentMatchers.anyString;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup(){
        System.out.println("HelloWorld, from BeforeEach");
        MockitoAnnotations.openMocks(this); // creates auto closeable resources for each test method
    }

    @Test
   public void testFindProductByTitleSuccess() throws ProductNotFoundException, InvalidTitleException {
       //Arrange
       Category category = new Category();
       category.setCategoryName("mock-category-name");

       Price price = new Price();
       price.setAmount(100.0);
       price.setCurrency("rupees");
       price.setDiscount(10);

       String mockTitle = "mock-title";
       Product mockProduct = new Product();
       mockProduct.setTitle(mockTitle);
       mockProduct.setDescription("mock-description");
       mockProduct.setImage("mock-url");
       mockProduct.setCategory(category);
       mockProduct.setPrice(price);

       Mockito.when(productRepository.findProductByTitle(mockTitle)).thenReturn(mockProduct);

       // Act
       ProductResponseDTO productResponseDTO = productService.findProductByTitle(mockTitle);

       // Assert
       Assertions.assertEquals(mockProduct.getTitle(), productResponseDTO.getTitle());
       Assertions.assertEquals(mockProduct.getDescription(), productResponseDTO.getDescription());
       Assertions.assertEquals(mockProduct.getImage(), productResponseDTO.getImage());
       Assertions.assertEquals(mockProduct.getPrice().getAmount(), productResponseDTO.getPrice());
       Assertions.assertEquals(mockProduct.getCategory().getCategoryName(), productResponseDTO.getCategory());

   }

   @Test
   public void testFindProductByTitleAndRepoReturnsNull() throws ProductNotFoundException {
        // Arrange
       Mockito.when(productRepository.findProductByTitle(anyString())).thenReturn(null);

       // Act and Assert
       Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findProductByTitle("foo"));
   }

   @Test
   public void testFindProductByTitleWhereTitleIsNull() {
       //Arrange
       Price mockPrice = new Price();
       mockPrice.setAmount(100);

       Category mockCategory = new Category();
       mockCategory.setCategoryName("mockCategory");

       String testTitle = null;
       Product mockProduct = new Product();
       mockProduct.setTitle(testTitle);
       mockProduct.setDescription("testDescription");
       mockProduct.setPrice(mockPrice);
       mockProduct.setCategory(mockCategory);

       Mockito.when(productRepository.findProductByTitle(testTitle)).thenReturn(mockProduct);

       // Act and Assert
       Assertions.assertThrows(InvalidTitleException.class, ()->productService.findProductByTitle(testTitle));
   }
}
