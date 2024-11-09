package com.scaler.DBInheritanceDemo.perClass.repository;

import com.scaler.DBInheritanceDemo.perClass.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerClassInstructorRepository extends JpaRepository<Instructor, Integer> {
}
