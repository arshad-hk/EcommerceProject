package com.scaler.EcomProductService.demo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    public static final String customQuery = "delete from book where id = :id";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String bookName;

    @ManyToOne
    //@JoinColumn(name = "author_id", referencedColumnName = "id")
    Author author;

    public Book (String bookName, Author author){
        this.bookName = bookName;
        this.author = author;
    }

    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author=" + (author != null ? author.getId() : null) +
                '}';
    }


}
















