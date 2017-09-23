package com.lastminute.taxesquiz.sale.language.formatter.service;

import com.lastminute.taxesquiz.sale.checkout.model.Receipt;
import com.lastminute.taxesquiz.sale.language.formatter.config.FormatterConfig;
import com.lastminute.taxesquiz.sale.order.basket.item.model.BasketItem;
import com.lastminute.taxesquiz.sale.order.basket.model.Basket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Formattable;

import static com.lastminute.taxesquiz.util.BasketMockUtil.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {FormatterConfig.class})
public class FormatterServiceTest {

    @Autowired
    FormatterService formatterService;

    @Test
    public void formatProductTest(){
        formatProductTest("book", "book", "", false);
        formatProductTest("music CD", "music CD", "", false);
        formatProductTest("chocolate bar", "chocolate bar", "", false);
        formatProductTest("imported box of chocolates", "chocolates", "box of", true);
        formatProductTest("imported bottle of perfume", "perfume", "bottle of", true);
        formatProductTest("bottle of perfume", "perfume", "bottle of", false);
        formatProductTest("packet of headache pills", "headache pills", "packet of", false);
    }

    @Test
    public void formatTest(){
        formatTest("5 product: 0.00", basketItem(
                product("product", "", false),
                new BigDecimal("0.00"),
                5
        ));
        formatTest("1 book: 12.49", basketItem(
                bookProduct("book", "", false),
                new BigDecimal("12.49"),
                1
        ));
        formatTest("1 music CD: 16.49", basketItem(
                defaultTaxableProduct("music CD", "",false),
                new BigDecimal("16.49"),
                1
        ));
        formatTest("1 chocolate bar: 0.85", basketItem(
                foodProduct("chocolate bar", "",false),
                new BigDecimal("0.85"),
                1
        ));
        formatTest("1 imported box of chocolates: 10.50", basketItem(
                foodProduct("chocolates", "box of", true),
                new BigDecimal("10.50"),
                1
        ));
        formatTest("1 imported bottle of perfume: 54.65", basketItem(
                defaultTaxableProduct("perfume","bottle of", true),
                new BigDecimal("54.65"),
                1
        ));
        formatTest("1 bottle of perfume: 20.89", basketItem(
                defaultTaxableProduct("perfume","bottle of",false),
                new BigDecimal("20.89"),
                1
        ));
        formatTest("1 packet of headache pills: 9.75", basketItem(
                medicalProduct("headache pills", "packet of", false),
                new BigDecimal("9.75"),
                1
        ));
    }

    @Test
    public void formatBasketTest(){
        String expectedBasket1 = "1 book: 12.49" + System.lineSeparator()+
                                "1 music CD: 16.49" + System.lineSeparator() +
                                "1 chocolate bar: 0.85" + System.lineSeparator();
        formatTest(expectedBasket1, basket1WithTaxes());

        String expectedBasket2 = "1 imported box of chocolates: 10.50" + System.lineSeparator()+
                                "1 imported bottle of perfume: 54.65" + System.lineSeparator();
        formatTest(expectedBasket2, basket2WithTaxes());

        String expectedBasket3 = "1 imported bottle of perfume: 32.19" + System.lineSeparator() +
                                 "1 bottle of perfume: 20.89" + System.lineSeparator() +
                                 "1 packet of headache pills: 9.75" + System.lineSeparator() +
                                 "1 imported box of chocolates: 11.85" + System.lineSeparator();
        formatTest(expectedBasket3, basket3WithTaxes());
    }

    @Test
    public void formatReceiptTest(){
        String expectedReceipt1 = "1 book: 12.49" + System.lineSeparator()+
                                  "1 music CD: 16.49" + System.lineSeparator() +
                                  "1 chocolate bar: 0.85" + System.lineSeparator() +
                                  "Sales Taxes: 1.50" + System.lineSeparator() +
                                  "Total: 29.83";
        formatTest(expectedReceipt1, new Receipt().basket(basket1WithTaxes())
                                                  .totalTaxes(new BigDecimal("1.50"))
                                                  .total(new BigDecimal("29.83")));

        String expectedReceipt2 = "1 imported box of chocolates: 10.50" + System.lineSeparator() +
                                  "1 imported bottle of perfume: 54.65" + System.lineSeparator() +
                                  "Sales Taxes: 7.65" + System.lineSeparator() +
                                  "Total: 65.15";
        formatTest(expectedReceipt2, new Receipt().basket(basket2WithTaxes())
                                                  .totalTaxes(new BigDecimal("7.65"))
                                                  .total(new BigDecimal("65.15")));

        String expectedReceipt3 = "1 imported bottle of perfume: 32.19" + System.lineSeparator() +
                                  "1 bottle of perfume: 20.89" + System.lineSeparator() +
                                  "1 packet of headache pills: 9.75" + System.lineSeparator() +
                                  "1 imported box of chocolates: 11.85" + System.lineSeparator() +
                                  "Sales Taxes: 6.70" + System.lineSeparator() +
                                  "Total: 74.68";
        formatTest(expectedReceipt3, new Receipt().basket(basket3WithTaxes())
                                                  .totalTaxes(new BigDecimal("6.70"))
                                                  .total(new BigDecimal("74.68")));
    }

    private <T extends Formattable> void formatTest(String expected, T toFormat){
        String formatted = formatterService.format(toFormat);
        assertEquals(expected, formatted);
    }

    private void formatProductTest(String expected, String productDescription, String packaging, boolean imported){
        formatTest(expected, product(productDescription, packaging, imported));
    }

}
