package com.scaler.EcomProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "ECOM_ORDER")
@Getter
@Setter
public class Order extends BaseModel{
    private double price;
    @ManyToMany
    List<Product> products;
}
