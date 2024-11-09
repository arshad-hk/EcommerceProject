package com.scaler.EcomProductService;

import com.scaler.EcomProductService.demo.AuthorService;
import com.scaler.EcomProductService.service.InitService;
import com.scaler.EcomProductService.demo.Author;
import com.scaler.EcomProductService.demo.AuthorRepository;
import com.scaler.EcomProductService.demo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication()
public class EcomProductServiceApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("InitServiceImpl")
	InitService initService;

	//@Autowired
	AuthorRepository authorRepository;

	//@Autowired
	AuthorService authorService;
	public static void main(String[] args) {
		SpringApplication.run(EcomProductServiceApplication.class, args);
		System.out.println("Print in main function");
	}

	@Override
	public void run(String... args) throws Exception {
		initService.initialise();

//		Author author = new Author("Ashok Kumar", 1);
//
//		Book book1 = new Book("Book1", author);
//		Book book2 = new Book("Book2", author);
//		Book book3 = new Book("Book3", author);
//		author.setBooks(List.of(book1, book2, book3));
//
//		authorRepository.save(author);
//
//		display();

	}

	public void display(){
		Author savedAuthor = authorService.getAuthorWithLazyBooks(1);
		System.out.println("Author with books:" + savedAuthor);
		List<Book> books = savedAuthor.getBooks();
		System.out.println(books);
	}

}
