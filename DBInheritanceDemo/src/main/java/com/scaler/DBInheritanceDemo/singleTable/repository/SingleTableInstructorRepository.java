package com.scaler.DBInheritanceDemo.singleTable.repository;

import com.scaler.DBInheritanceDemo.singleTable.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleTableInstructorRepository extends JpaRepository<Instructor, Integer> {
}
