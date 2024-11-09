package com.scaler.DBInheritanceDemo.joined.repository;

import com.scaler.DBInheritanceDemo.joined.ScalerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScalerUserRepository extends JpaRepository<ScalerUser,Integer> {
}
