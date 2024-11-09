package com.scaler.DBInheritanceDemo.mappedSuperClass.repository;


import com.scaler.DBInheritanceDemo.mappedSuperClass.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSCStudentRepository extends JpaRepository<Student, Integer> {
}
