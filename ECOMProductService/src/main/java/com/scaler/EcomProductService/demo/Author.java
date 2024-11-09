package com.scaler.EcomProductService.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    public Author(String name, int id){
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Book> books = new ArrayList<>();

    public static final String customQuery = "select * from author where id=:id";

    public Author() {

    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
//
//    public String toStringWithoutBooks() {
//        return "Author{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//
//    public String toStringWithName() {
//        return "Author{" +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
