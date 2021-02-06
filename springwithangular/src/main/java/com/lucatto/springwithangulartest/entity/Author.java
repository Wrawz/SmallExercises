// off: spring.jpa.hibernate.ddl-auto=create
package com.lucatto.springwithangulartest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "birth")
    private Date birth;
    @Column(name = "death")
    private Date death;
    @Column(name = "country")
    private String country;
    @Column(name = "about")
    private String about;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Book> books;
}
