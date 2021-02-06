package com.lucatto.springwithangulartest.dto;

import com.lucatto.springwithangulartest.entity.*;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Order order;
    private Set<Book> books;

}
