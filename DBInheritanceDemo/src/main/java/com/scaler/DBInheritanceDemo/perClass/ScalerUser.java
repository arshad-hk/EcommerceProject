package com.scaler.DBInheritanceDemo.perClass;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "scaler_user_per_class")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ScalerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
}
