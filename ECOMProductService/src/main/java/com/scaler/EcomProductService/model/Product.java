package com.scaler.EcomProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Price price;
}

// cardinality
//      P : C
//(P)   1 : 1
//(C)   M : 1
//-------------------
//      M : 1
