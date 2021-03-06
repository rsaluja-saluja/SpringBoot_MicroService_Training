package com.jpa.mappings.repo;

import java.util.List;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.mappings.entity.Book;
import com.jpa.mappings.entity.Page;

@Repository
@Transactional
public interface PageRepository extends JpaRepository<Page, Long> {

	List<Page> findByBook(Book book, Sort sort);
}
