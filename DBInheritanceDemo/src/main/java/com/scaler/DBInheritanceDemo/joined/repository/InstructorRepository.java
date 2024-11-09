package com.scaler.DBInheritanceDemo.joined.repository;

import com.scaler.DBInheritanceDemo.joined.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
