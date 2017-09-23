package com.lastminute.taxesquiz.sale.checkout.service.impl;

import com.lastminute.taxesquiz.sale.checkout.model.Receipt;
import com.lastminute.taxesquiz.sale.checkout.service.CheckoutService;
import com.lastminute.taxesquiz.sale.order.basket.model.Basket;
import com.lastminute.taxesquiz.sale.order.basket.parser.BasketParser;
import com.lastminute.taxesquiz.sale.tax.service.TaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    @Autowired
    private TaxService taxService;

    @Override
    public Receipt issueReceipt( Basket basket ) {
        logger.info("start issuing receipt for basket [{}]", basket);
        Receipt receipt = new Receipt();
        BigDecimal totalWithoutTaxes = calculateTotal(basket);
        basket = taxService.applyTaxes(basket);
        BigDecimal totalWithTaxes = calculateTotal(basket);
        receipt.setBasket(basket);
        receipt.setTotal(totalWithTaxes);
        receipt.setTotalTaxes(totalWithTaxes.subtract(totalWithoutTaxes));
        logger.info("issued receipt [{}]", receipt);
        return receipt;
    }

    private BigDecimal calculateTotal( Basket basket ){
        BigDecimal total = basket.getBasketItems().stream()
                .map( basketItem -> basketItem.getPrice().multiply(new BigDecimal(basketItem.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }

}
