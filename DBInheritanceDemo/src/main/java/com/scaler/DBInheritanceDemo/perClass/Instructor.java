package com.scaler.DBInheritanceDemo.perClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "instructor_per_class")
@Data
public class Instructor extends ScalerUser {
    private double instructorRating;
}
