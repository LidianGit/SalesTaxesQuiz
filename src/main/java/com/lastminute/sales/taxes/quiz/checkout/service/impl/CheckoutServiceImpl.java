package com.lastminute.sales.taxes.quiz.checkout.service.impl;

import com.lastminute.sales.taxes.quiz.checkout.service.CheckoutService;
import com.lastminute.sales.taxes.quiz.basket.model.Basket;
import com.lastminute.sales.taxes.quiz.common.model.Receipt;
import com.lastminute.sales.taxes.quiz.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private TaxService taxService;

    @Override
    public Receipt issueReceipt( Basket basket ) {
        Receipt receipt = new Receipt();
        BigDecimal totalWithoutTaxes = calculateTotal(basket);
        basket = taxService.applyTaxes(basket);
        BigDecimal totalWithTaxes = calculateTotal(basket);
        receipt.setBasket(basket);
        receipt.setTotal(totalWithTaxes);
        receipt.setTotalSalesTaxes(totalWithTaxes.subtract(totalWithoutTaxes));
        return receipt;
    }

    private BigDecimal calculateTotal( Basket basket ){
        BigDecimal total = basket.getBasketItems().stream()
                .map( basketItem -> basketItem.getPrice().multiply(new BigDecimal(basketItem.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }

}