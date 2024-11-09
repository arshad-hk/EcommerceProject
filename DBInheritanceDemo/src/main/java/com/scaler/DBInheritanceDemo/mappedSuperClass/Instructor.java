package com.scaler.DBInheritanceDemo.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "instructor_msc")
@Data
public class Instructor extends ScalerUser {
    private double instructorRating;
}
