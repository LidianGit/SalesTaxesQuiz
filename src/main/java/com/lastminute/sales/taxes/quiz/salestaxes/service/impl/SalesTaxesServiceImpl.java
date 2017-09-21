package com.lastminute.sales.taxes.quiz.salestaxes.service.impl;

import com.lastminute.sales.taxes.quiz.common.model.Basket;
import com.lastminute.sales.taxes.quiz.common.model.BasketItem;
import com.lastminute.sales.taxes.quiz.common.model.ProductCategory;
import com.lastminute.sales.taxes.quiz.common.model.SaleTax;
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
        BigDecimal salesTaxes = basketItem.getProduct().getCategories().stream()
                .map(this::sumTaxes)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal priceWithTaxes = price.add( price.multiply(salesTaxes).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP) );
        basketItem.setPrice(priceWithTaxes);
        return basketItem;
    }

    private BigDecimal sumTaxes(ProductCategory productCategory){
        return productCategory.getSaleTaxes().stream()
                .map(SaleTax::getPercent)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
