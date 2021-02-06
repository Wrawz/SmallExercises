package com.lucatto.springwithangulartest.dao;

import com.lucatto.springwithangulartest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://127.0.0.1:4200")
public interface OrderRepository extends JpaRepository<Order, Long> {
}
