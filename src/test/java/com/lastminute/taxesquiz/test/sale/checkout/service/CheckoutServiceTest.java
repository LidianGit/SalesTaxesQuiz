package com.lastminute.taxesquiz.test.sale.checkout.service;

import com.lastminute.taxesquiz.sale.checkout.config.CheckoutConfig;
import com.lastminute.taxesquiz.sale.checkout.model.Receipt;
import com.lastminute.taxesquiz.sale.checkout.service.CheckoutService;
import com.lastminute.taxesquiz.test.sale.checkout.service.config.CheckoutServiceTestConfig;
import com.lastminute.taxesquiz.sale.basket.model.Basket;
import com.lastminute.taxesquiz.sale.tax.service.TaxService;
import com.lastminute.taxesquiz.util.BasketMockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {CheckoutServiceTestConfig.class, CheckoutConfig.class})
public class CheckoutServiceTest {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private TaxService taxService;

    @Test
    public void issueReceiptBasket1(){
        assertReceipt( BasketMockUtil.basket1(), BasketMockUtil.basket1WithTaxes(), "1.50", "29.83");
    }

    @Test
    public void issueReceiptBasket2(){
        assertReceipt( BasketMockUtil.basket2(), BasketMockUtil.basket2WithTaxes(), "7.65", "65.15");
    }

    @Test
    public void issueReceiptBasket3(){
        assertReceipt( BasketMockUtil.basket3(), BasketMockUtil.basket3WithTaxes(), "6.70", "74.68");
    }

    private void assertReceipt(Basket basket, Basket basketWithTaxes, String exTotalSalesTaxes, String exTotal){
        when(taxService.applyTaxes(basket)).thenReturn(basketWithTaxes);
        Receipt receipt = checkoutService.issueReceipt( basket );
        assertEquals(basketWithTaxes, receipt.getBasket());
        assertEquals(exTotalSalesTaxes, receipt.getTotalTaxes().toString());
        assertEquals(exTotal, receipt.getTotal().toString());
    }

}
