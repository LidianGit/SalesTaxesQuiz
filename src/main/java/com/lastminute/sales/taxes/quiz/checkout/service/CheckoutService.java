package com.lastminute.sales.taxes.quiz.checkout.service;

import com.lastminute.sales.taxes.quiz.common.model.Basket;
import com.lastminute.sales.taxes.quiz.common.model.Receipt;

public interface CheckoutService {

    Receipt issueReceipt(Basket basket);

}
