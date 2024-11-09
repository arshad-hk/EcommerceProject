package com.scaler.DBInheritanceDemo.perClass.repository;

import com.scaler.DBInheritanceDemo.perClass.ScalerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerClassScalerUserRepository extends JpaRepository<ScalerUser,Integer> {
}
