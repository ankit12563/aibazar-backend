package com.ankitregmi.ecommerce.service;

import com.ankitregmi.ecommerce.dataTransferObject.Purchase;
import com.ankitregmi.ecommerce.dataTransferObject.PurchaseResponse;
import com.ankitregmi.ecommerce.entity.Customer;
import com.ankitregmi.ecommerce.entity.Order;
import com.ankitregmi.ecommerce.entity.OrderItem;
import com.ankitregmi.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImp implements CheckoutService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //populate order with order items
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        //populate address
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //populate customer with order
        Customer customer = purchase.getCustomer();
        String theEmail = customer.getEmail(); // check existing email

        Customer customerFromDb = customerRepository.findByEmail(theEmail);

        if(customerFromDb != null) {
            customer = customerFromDb;
        }
        customer.add(order);

        //save in the dateBase
        customerRepository.save(customer);

        //return response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
