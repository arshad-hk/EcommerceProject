package com.scaler.DBInheritanceDemo.mappedSuperClass.repository;

import com.scaler.DBInheritanceDemo.mappedSuperClass.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSCInstructorRepository extends JpaRepository<Instructor, Integer> {
}
