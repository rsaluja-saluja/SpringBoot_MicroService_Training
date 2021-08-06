package com.jpa.mappings.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.mappings.entity.Book;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long>{

	Book findByIsbn(String isbn);
}
