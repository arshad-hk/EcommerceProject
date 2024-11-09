package com.scaler.DBInheritanceDemo.perClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "student_per_class")
@Data
public class Student extends ScalerUser {
    private String batchName;
    private double psp;
}
