package com.lastminute.taxesquiz.sale.checkout.service;

import com.lastminute.taxesquiz.sale.basket.model.Basket;
import com.lastminute.taxesquiz.sale.checkout.model.Receipt;

public interface CheckoutService {

    Receipt issueReceipt(Basket basket);

}
