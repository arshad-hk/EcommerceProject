package com.scaler.DBInheritanceDemo.joined;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "instructor_joined")
@Data
public class Instructor extends ScalerUser{
    private double instructorRating;
}
