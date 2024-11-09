package com.scaler.DBInheritanceDemo.joined;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "student_joined")
@Data
public class Student extends ScalerUser{
    private String batchName;
    private double psp;
}
