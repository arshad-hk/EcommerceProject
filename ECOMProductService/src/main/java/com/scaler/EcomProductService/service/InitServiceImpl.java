package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.model.Category;
import com.scaler.EcomProductService.model.Order;
import com.scaler.EcomProductService.model.Price;
import com.scaler.EcomProductService.model.Product;
import com.scaler.EcomProductService.repository.CategoryRepository;
import com.scaler.EcomProductService.repository.OrderRepository;
import com.scaler.EcomProductService.repository.PriceRepository;
import com.scaler.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("InitServiceImpl")
public class InitServiceImpl implements InitService{

    private ProductRepository productRepository;
    private PriceRepository priceRepository;
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;

    public InitServiceImpl(ProductRepository productRepository, PriceRepository priceRepository, OrderRepository orderRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initialise() {
        Category electronics = new Category();
        electronics.setCategoryName("Electronics");

        electronics = categoryRepository.save(electronics); // insert and update -> upsert

        Price priceIphone = new Price();
        priceIphone.setCurrency("INR");
        priceIphone.setAmount(100000);
        priceIphone.setDiscount(0);

        Price priceMacbook = new Price();
        priceMacbook.setCurrency("INR");
        priceMacbook.setAmount(200000);
        priceMacbook.setDiscount(0);

        Price priceWatch = new Price();
        priceWatch.setCurrency("INR");
        priceWatch.setAmount(40000);
        priceWatch.setDiscount(0);

        Price pricePS5 = new Price();
        pricePS5.setCurrency("INR");
        pricePS5.setAmount(50000);
        pricePS5.setDiscount(0);

        priceIphone = priceRepository.save(priceIphone);
        priceMacbook = priceRepository.save(priceMacbook);
        priceWatch = priceRepository.save(priceWatch);
        pricePS5 = priceRepository.save(pricePS5);


        Product iphone = new Product();
        iphone.setTitle("IPhone 15 Pro");
        iphone.setDescription("Best Iphone ever");
        iphone.setImage("http://someImageURl");
        iphone.setPrice(priceIphone);
        iphone.setCategory(electronics);
        iphone = productRepository.save(iphone);

        Product iphone14 = new Product();
        iphone14.setTitle("IPhone 14 Pro Max");
        iphone14.setDescription("Best Iphone 14 ever");
        iphone14.setImage("http://someImageURl");
        iphone14.setPrice(pricePS5);
        iphone14.setCategory(electronics);
        iphone14 = productRepository.save(iphone14);

        Product iphoneSE = new Product();
        iphoneSE.setTitle("IPhone SE");
        iphoneSE.setDescription("Small Iphone ever");
        iphoneSE.setImage("http://someImageURl");
        iphoneSE.setPrice(priceWatch);
        iphoneSE.setCategory(electronics);
        iphoneSE = productRepository.save(iphoneSE);

        Product macbook = new Product();
        macbook.setTitle("Macbook Pro 16");
        macbook.setDescription("Best macbook ever");
        macbook.setImage("http://someImageURl");
        macbook.setPrice(priceMacbook);
        macbook.setCategory(electronics);
        macbook = productRepository.save(macbook);

        Product watch = new Product();
        watch.setTitle("Watch Series 10");
        watch.setDescription("Best watch ever");
        watch.setImage("http://someImageURl");
        watch.setPrice(priceWatch);
        watch.setCategory(electronics);
        watch = productRepository.save(watch);

        Product ps5 = new Product();
        ps5.setTitle("PlayStation5");
        ps5.setDescription("Best playstation ever");
        ps5.setImage("http://someImageURl");
        ps5.setPrice(pricePS5);
        ps5.setCategory(electronics);
        ps5 = productRepository.save(ps5);

        Order order = new Order();
        order.setProducts(List.of(iphone, macbook, watch, ps5, iphone14, iphoneSE));
        order = orderRepository.save(order);
    }
}
