package com.lucatto.springwithangulartest.controller;

import com.lucatto.springwithangulartest.dto.Purchase;
import com.lucatto.springwithangulartest.dto.PurchaseResponse;
import com.lucatto.springwithangulartest.service.CheckoutService;
import com.lucatto.springwithangulartest.service.CheckoutServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@CrossOrigin("http://127.0.0.1:4200") @RestController @RequestMapping("/api/checkout")
public class CheckoutController {
    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        return checkoutService.placeOrder(purchase);
    }

}
