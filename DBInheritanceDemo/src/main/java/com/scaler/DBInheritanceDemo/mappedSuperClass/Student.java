package com.scaler.DBInheritanceDemo.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "student_msc")
@Data
public class Student extends ScalerUser {
    private String batchName;
    private double psp;
}
