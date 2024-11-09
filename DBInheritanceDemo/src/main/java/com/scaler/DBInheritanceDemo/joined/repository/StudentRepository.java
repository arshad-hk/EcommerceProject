package com.scaler.DBInheritanceDemo.joined.repository;

import com.scaler.DBInheritanceDemo.joined.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
