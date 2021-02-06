package com.lucatto.springwithangulartest.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="order_tracking_number")
    private String orderTrackingNumber;
    @Column(name="total_price")
    private BigDecimal totalPrice;
    @Column(name="total_quantity")
    private int totalQuantity;
    @Column(name="status_2")
    private String status;
    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;
    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<Book> books = new HashSet<>();


    public void add(Book book) {
        if (book != null) {
            if (books == null) books = new HashSet<>();
            books.add(book);
            book.setOrder(this);
        }
    }
}
