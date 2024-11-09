package com.scaler.DBInheritanceDemo.mappedSuperClass;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class ScalerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
}
