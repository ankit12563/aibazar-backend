package com.ankitregmi.ecommerce.dataTransferObject;

import com.ankitregmi.ecommerce.entity.Address;
import com.ankitregmi.ecommerce.entity.Customer;
import com.ankitregmi.ecommerce.entity.Order;
import com.ankitregmi.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;
}
