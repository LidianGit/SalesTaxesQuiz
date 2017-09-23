package com.lastminute.taxesquiz.sale.tax.util;

import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import com.lastminute.taxesquiz.sale.tax.model.Tax;
import com.lastminute.taxesquiz.util.BasketMockUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorUtilTest {

    @Test
    public void sumTaxesBasketItemTaxes(){
        BigDecimal taxesSum = TaxCalculatorUtil.sumTaxes(
                BasketMockUtil.basketItem(
                        BasketMockUtil.defaultTaxableProduct("bottle of perfume", false),
                        new BigDecimal("18.99"),
                        new Integer(1)
                )
        );
        assertEquals("1.90", taxesSum.toString());
        taxesSum = TaxCalculatorUtil.sumTaxes(
                BasketMockUtil.basketItem(
                        BasketMockUtil.defaultTaxableProduct("bottle of perfume", true),
                        new BigDecimal("27.99"),
                        new Integer(1)
                )
        );
        assertEquals("4.20", taxesSum.toString());
    }

    @Test
    public void sumProductCategoryTaxesTest(){
        BigDecimal taxesSum = TaxCalculatorUtil.sumTaxes(new BigDecimal("10.00"), BasketMockUtil.defaultProductCategory());
        assertEquals("1.00", taxesSum.toString());

        List<Tax> multiTaxes = new ArrayList<>();
        multiTaxes.add( new Tax().percent(new BigDecimal("5.00") ));
        multiTaxes.add( new Tax().percent(new BigDecimal("5.00") ));

        ProductCategory multiTaxesProductCat = BasketMockUtil.productCategory("MULTITAXES", "multiple taxes", multiTaxes );

        taxesSum = TaxCalculatorUtil.sumTaxes(new BigDecimal("10.00"), multiTaxesProductCat);
        assertEquals("1.00", taxesSum.toString());
    }

    @Test
    public void calculateTaxTest(){
        //1 imported box of chocolates: 10.50 - 10.00 = 0.50 ( 0.50 importedTax )
        BigDecimal importedTax = TaxCalculatorUtil.calculateTax(new BigDecimal("10.00"), new BigDecimal("5"), 2);
        assertEquals( "0.50", importedTax.toString());

        //1 imported bottle of perfume: 54.65 - 47.50 = 7.15 ( 2.40 importedTax + 4.75 defaultTax )
        importedTax = TaxCalculatorUtil.calculateTax(new BigDecimal("47.50"), new BigDecimal("5"), 2);
        assertEquals( "2.40", importedTax.toString());
        BigDecimal defaultTax = TaxCalculatorUtil.calculateTax( new BigDecimal("47.50"), new BigDecimal("10"), 2);
        assertEquals( "4.75", defaultTax.toString());
        assertEquals("7.15", defaultTax.add(importedTax).toString());

        //1 imported bottle of perfume: 32.19 - 27.99 = 4.20 ( 1.40 importedTax + 2.80 defaultTax )
        importedTax = TaxCalculatorUtil.calculateTax(new BigDecimal("27.99"), new BigDecimal("5"), 2);
        assertEquals( "1.40", importedTax.toString());
        defaultTax = TaxCalculatorUtil.calculateTax(new BigDecimal("27.99"), new BigDecimal("10"), 2);
        assertEquals( "2.80", defaultTax.toString());
        assertEquals("4.20", importedTax.add(defaultTax).toString());

        //1 bottle of perfume: 20.89 - 18.99 = 1.90 ( 1.90 defaultTax )
        defaultTax = TaxCalculatorUtil.calculateTax(new BigDecimal("18.99"), new BigDecimal("10"), 2);
        assertEquals( "1.90", defaultTax.toString());

        //1 imported box of chocolates: 11.85 - 11.25 = 0.60 ( 0.60 importedTax )
        importedTax = TaxCalculatorUtil.calculateTax(new BigDecimal("11.25"), new BigDecimal("5"), 2);
        assertEquals( "0.60", importedTax.toString());
    }

}
