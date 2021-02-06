package com.lucatto.springwithangulartest.dto;

import lombok.Data;

@Data // @Data only creates constructors for final fields
public class PurchaseResponse {

    private final String orderTrackingNumber;
}
