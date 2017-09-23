package com.lastminute.taxesquiz.sale.tax.service.impl;

import com.lastminute.taxesquiz.sale.order.basket.model.Basket;
import com.lastminute.taxesquiz.sale.order.basket.item.model.BasketItem;
import com.lastminute.taxesquiz.sale.tax.service.TaxService;
import com.lastminute.taxesquiz.sale.tax.util.TaxCalculatorUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TaxServiceImpl implements TaxService {

    @Override
    public Basket applyTaxes(Basket basket) {
        basket.getBasketItems().forEach(this::applyTaxes);
        return basket;
    }

    private BasketItem applyTaxes(BasketItem basketItem){
        BigDecimal price = basketItem.getPrice();
        BigDecimal taxesSum = TaxCalculatorUtil.sumTaxes(basketItem);
        BigDecimal priceWithTaxes = price.add(taxesSum);
        basketItem.setPrice(priceWithTaxes);
        return basketItem;
    }

}
