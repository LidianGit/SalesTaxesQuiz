package com.lastminute.sales.taxes.quiz.salestaxes.service.impl;

import com.lastminute.sales.taxes.quiz.common.model.Basket;
import com.lastminute.sales.taxes.quiz.common.model.BasketItem;
import com.lastminute.sales.taxes.quiz.common.model.ProductCategory;
import com.lastminute.sales.taxes.quiz.salestaxes.service.SalesTaxesService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SalesTaxesServiceImpl implements SalesTaxesService {

    @Override
    public Basket applyTaxes(Basket basket) {
        basket.getBasketItems().forEach(
                this::applyTaxes
        );
        return basket;
    }

    private BasketItem applyTaxes(BasketItem basketItem){
        BigDecimal price = basketItem.getPrice();

        BigDecimal salesTaxesSum = basketItem.getProduct().getCategories().stream()
                .map(productCategory -> sumTaxes(price, productCategory))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal priceWithTaxes = price.add( salesTaxesSum );
        basketItem.setPrice(priceWithTaxes);
        return basketItem;
    }

    public BigDecimal sumTaxes(BigDecimal price, ProductCategory productCategory){
        return productCategory.getSaleTaxes().stream()
                .map(saleTax -> price.multiply(saleTax.getPercent())
                                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static final BigDecimal applyTaxToPrice(BigDecimal price){
        price.multiply(price)
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))
    }

}
