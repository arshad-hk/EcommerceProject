package com.scaler.EcomProductService.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import static com.scaler.EcomProductService.demo.Book.customQuery;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = customQuery, nativeQuery = true)
    boolean deleteBookById(long id);
}
