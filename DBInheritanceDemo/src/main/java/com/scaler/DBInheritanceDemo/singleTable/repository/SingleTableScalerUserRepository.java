package com.scaler.DBInheritanceDemo.singleTable.repository;

import com.scaler.DBInheritanceDemo.singleTable.ScalerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleTableScalerUserRepository extends JpaRepository<ScalerUser,Integer> {
}
