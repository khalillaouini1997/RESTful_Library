package com.pi.restful_library.repository;


import com.pi.restful_library.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Long> {
}