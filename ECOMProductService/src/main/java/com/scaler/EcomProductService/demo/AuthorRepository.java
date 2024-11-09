package com.scaler.EcomProductService.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(value = Author.customQuery, nativeQuery = true)
    Author findByIdCustomQuery(@Param("id") Integer id);
}
