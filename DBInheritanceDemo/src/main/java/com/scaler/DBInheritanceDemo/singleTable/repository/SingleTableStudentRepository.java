package com.scaler.DBInheritanceDemo.singleTable.repository;


import com.scaler.DBInheritanceDemo.singleTable.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleTableStudentRepository extends JpaRepository<Student, Integer> {
}
