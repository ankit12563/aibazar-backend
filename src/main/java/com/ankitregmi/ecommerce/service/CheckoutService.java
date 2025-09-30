package com.ankitregmi.ecommerce.service;

import com.ankitregmi.ecommerce.dataTransferObject.Purchase;
import com.ankitregmi.ecommerce.dataTransferObject.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
