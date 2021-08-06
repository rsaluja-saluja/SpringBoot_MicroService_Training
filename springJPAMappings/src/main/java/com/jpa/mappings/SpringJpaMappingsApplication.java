package com.jpa.mappings;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.mappings.entity.Book;
import com.jpa.mappings.entity.Page;
import com.jpa.mappings.repo.BookRepository;
import com.jpa.mappings.repo.PageRepository;

@SpringBootApplication

public class SpringJpaMappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaMappingsApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner mappingDemo(BookRepository bookRepository,
                                         PageRepository pageRepository) {
        return args -> {

            // create a new book
            Book book = new Book("Java 101", "John Doe", "123456");

            // save the book
            bookRepository.save(book);

            // create and save new pages
            pageRepository.save(new Page(1, "Introduction contents", "Introduction", book));
            pageRepository.save(new Page(65, "Java 8 contents", "Java 8", book));
            pageRepository.save(new Page(95, "Concurrency contents", "Concurrency", book));
            
           List<Page> pages = pageRepository.findByBook(book, null);
           System.out.println("pages:"+pages.size());
           System.out.println(pages.get(0));
            Book b = bookRepository.findByIsbn("123456");
            System.out.println(b.toString());
        };
    }
}


