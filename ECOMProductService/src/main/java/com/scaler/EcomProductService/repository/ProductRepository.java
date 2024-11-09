package com.scaler.EcomProductService.repository;

import com.scaler.EcomProductService.model.Price;
import com.scaler.EcomProductService.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findProductByTitle(String title);
    Product findProductByTitleAndDescription(String title, String description);
    Product findProductByTitleOrDescription(String title, String description);
    Product findByPriceLessThanEqual(Price price); // <= price
    Product findByPriceLessThan(Price price); // < price
    Product findByPriceGreaterThanEqual(Price price); // >= price
    Product findProductByPrice_AmountGreaterThan(double amount);
    //Product findByPrice_AmountBetweenStartPriceAmountAndEndPriceAmount(double startPrice, double endPrice);
    Product findProductByPriceAmount(double amount);

    Page<Product> findByTitleContainsIgnoreCase(String title, Pageable pageable);

    @Override
    Page<Product> findAll(Pageable pageable);


}
