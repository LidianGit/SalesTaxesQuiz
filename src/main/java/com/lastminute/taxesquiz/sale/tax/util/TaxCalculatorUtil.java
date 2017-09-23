package com.lastminute.taxesquiz.sale.tax.util;

import com.lastminute.taxesquiz.sale.order.basket.item.model.BasketItem;
import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import com.lastminute.taxesquiz.math.rounding.rule.RoundingRuleType;
import com.lastminute.taxesquiz.math.util.MathUtil;

import java.math.BigDecimal;

public class TaxCalculatorUtil {

    public static BigDecimal sumTaxes(BasketItem basketItem){
        return basketItem.getProduct().getCategories().stream()
                .map(productCategory -> sumTaxes(basketItem.getPrice(), productCategory))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal sumTaxes(BigDecimal price, ProductCategory productCategory){
        return productCategory.getTaxes().stream()
                .map(tax -> calculateTax(price,tax.getPercentage(), 2))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal calculateTax(BigDecimal price, BigDecimal percentage, int scale){
        BigDecimal taxValue = MathUtil.percentOf(price, percentage, scale);
        return MathUtil.round( taxValue, scale, RoundingRuleType.ROUNDING_NEAREST_5);
    }

}
