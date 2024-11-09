package com.scaler.DBInheritanceDemo.mappedSuperClass.repository;

import com.scaler.DBInheritanceDemo.mappedSuperClass.ScalerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSCScalerUserRepository extends JpaRepository<ScalerUser,Integer> {
}
