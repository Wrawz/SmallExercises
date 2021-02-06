package com.lucatto.springwithangulartest.service;

import com.lucatto.springwithangulartest.dao.CustomerRepository;
import com.lucatto.springwithangulartest.dto.Purchase;
import com.lucatto.springwithangulartest.dto.PurchaseResponse;
import com.lucatto.springwithangulartest.entity.Book;
import com.lucatto.springwithangulartest.entity.Customer;
import com.lucatto.springwithangulartest.entity.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImplementation implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<Book> books = purchase.getBooks();
//        books.forEach(book -> books.add(book));
        books.addAll(books);

        // in case you had a 1-to-1 relationship table (between an address table and an order table)
        // and had an address to send
        // your physical product to and another to get the payment from,
        // you could do something like this:
//        populate order with billingAddress and shippingAddress
//        order.setBillingAddress(purchase.getBillingAddress());
//        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        //
        return UUID.randomUUID().toString();
    }
}
