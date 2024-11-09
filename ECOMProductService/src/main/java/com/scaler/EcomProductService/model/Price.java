package com.scaler.EcomProductService.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Price extends BaseModel{
    private String currency;
    private double amount;
    private double discount;
}
