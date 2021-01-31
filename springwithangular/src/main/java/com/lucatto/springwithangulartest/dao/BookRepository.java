package com.lucatto.springwithangulartest.dao;

import com.lucatto.springwithangulartest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://127.0.0.1:4200")
@RepositoryRestResource(collectionResourceRel = "authorId", path = "book-field")
public interface BookRepository extends JpaRepository<Book, Long> {
}
