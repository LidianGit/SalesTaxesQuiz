package com.lastminute.sales.taxes.quiz.checkout.service;

import com.lastminute.sales.taxes.quiz.checkout.config.CheckoutConfig;
import com.lastminute.sales.taxes.quiz.common.model.Basket;
import com.lastminute.sales.taxes.quiz.common.model.Receipt;
import com.lastminute.sales.taxes.quiz.salestaxes.service.SalesTaxesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.lastminute.sales.taxes.quiz.input.BasketMockUtil.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {CheckoutConfig.class})
@Configuration
public class CheckoutServiceTest {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private SalesTaxesService salesTaxesService;

    @Test
    public void issueReceiptBasket1(){
        assertReceipt( basket1(), basket1WithTaxes(), "1.50", "29.83");
    }

    @Test
    public void issueReceiptBasket2(){
        assertReceipt( basket2(), basket2WithTaxes(), "7.65", "65.15");
    }

    @Test
    public void issueReceiptBasket3(){
        assertReceipt( basket3(), basket3WithTaxes(), "6.70", "74.68");
    }

    private void assertReceipt(Basket basket, Basket basketWithTaxes, String exTotalSalesTaxes, String exTotal){
        when(salesTaxesService.applyTaxes(basket)).thenReturn(basketWithTaxes);
        Receipt receipt = checkoutService.issueReceipt( basket );
        assertEquals(basketWithTaxes, receipt.getBasket());
        assertEquals(exTotalSalesTaxes, receipt.getTotalSalesTaxes().toString());
        assertEquals(exTotal, receipt.getTotal().toString());
    }

    @Bean
    public SalesTaxesService salesTaxesService(){
        return mock(SalesTaxesService.class);
    }

}
