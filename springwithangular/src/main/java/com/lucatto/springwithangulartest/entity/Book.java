// off: spring.jpa.hibernate.ddl-auto=create
package com.lucatto.springwithangulartest.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @ManyToOne @JoinColumn(name = "orders_id", nullable = false)
    private Order order;
    @Column(name = "title")
    private String title;
    @Column(name = "synopsis")
    private String synopsis;
    @Column(name = "genre")
    private String genre;
    @Column(name = "original_language")
    private String original_language;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "available_languages")
    private String availableLanguages;
    @Column(name = "published_year")
    private int publishedYear;
//    @Column(name = "bought")
//    private boolean bought = false;
    // you can set those bought boolean values if you wanna sell each book separately
    // and change its value on the fly when the payment is made

}
