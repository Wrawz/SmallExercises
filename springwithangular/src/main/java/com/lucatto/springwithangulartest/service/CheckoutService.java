package com.lucatto.springwithangulartest.service;

import com.lucatto.springwithangulartest.dto.Purchase;
import com.lucatto.springwithangulartest.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
