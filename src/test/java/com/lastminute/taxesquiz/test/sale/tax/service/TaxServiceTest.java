package com.lastminute.taxesquiz.test.sale.tax.service;

import com.lastminute.taxesquiz.sale.order.basket.model.Basket;
import com.lastminute.taxesquiz.sale.order.basket.item.model.BasketItem;
import com.lastminute.taxesquiz.sale.tax.config.TaxConfig;
import com.lastminute.taxesquiz.sale.tax.service.TaxService;
import com.lastminute.taxesquiz.util.BasketMockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {TaxConfig.class})
@Configuration
public class TaxServiceTest {

    @Autowired
    private TaxService taxService;

    @Test
    public void applyTaxesToBasket1(){
        assertApplyTaxesToBasket( BasketMockUtil.basket1WithTaxes(), BasketMockUtil.basket1());
    }

    @Test
    public void applyTaxesToBasket2(){
        assertApplyTaxesToBasket( BasketMockUtil.basket2WithTaxes(), BasketMockUtil.basket2());
    }

    @Test
    public void applyTaxesToBasket3(){
        assertApplyTaxesToBasket( BasketMockUtil.basket3WithTaxes(), BasketMockUtil.basket3());
    }

    private void assertApplyTaxesToBasket( Basket exBasketWithTaxes, Basket basket){
        Basket basketWithTaxes = taxService.applyTaxes(basket);
        List<BasketItem> basketItems = basketWithTaxes.getBasketItems();
        List<BasketItem> exTaxesBasketItems = exBasketWithTaxes.getBasketItems();
        assertEquals( exTaxesBasketItems.size(), basketItems.size());
        for( int i=0; i< basketItems.size(); i++ ){
            assertEquals(exTaxesBasketItems.get(i).getPrice(), basketItems.get(i).getPrice());
        }
    }

}
