package com.lucatto.springwithangulartest.dao;

import com.lucatto.springwithangulartest.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://127.0.0.1:4200")
//@RepositoryRestResource(collectionResourceRel = "authorId", path = "book-field")
public interface BookRepository extends JpaRepository<Book, Long> {
//    @Query("SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%', :searchText, '%')) " +
//            "OR lower(b.synopsis) LIKE lower(concat('%', :searchText, '%'))")
//    Page<Book> myOwnQuery(@RequestParam("searchText") String searchText, Pageable pageable);
    Page<Book> findBooksById(@RequestParam("id") Long id, Pageable pageable);

    // these objects like "myOwnQuery" and "findBookById" are gonna be stored inside 127.0.0.1 like this:
    // http://127.0.0.1:8080/api/books/search/findBooksById
    // http://127.0.0.1:8080/api/books/search/myOwnQuery

    // e.g.: if you wanna pass data do the Rest API, type the following:
    // http://127.0.0.1:8080/api/books/search/findBooksById?id=1


    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);
    // its default query will be like this: SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',:name '%')

}
