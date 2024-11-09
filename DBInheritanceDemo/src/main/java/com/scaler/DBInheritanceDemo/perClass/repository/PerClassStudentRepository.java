package com.scaler.DBInheritanceDemo.perClass.repository;


import com.scaler.DBInheritanceDemo.perClass.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerClassStudentRepository extends JpaRepository<Student, Integer> {
}
