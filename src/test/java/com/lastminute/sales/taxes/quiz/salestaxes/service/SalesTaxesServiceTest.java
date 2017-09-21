package com.lastminute.sales.taxes.quiz.salestaxes.service;

import com.lastminute.sales.taxes.quiz.common.model.Basket;
import com.lastminute.sales.taxes.quiz.common.model.BasketItem;
import com.lastminute.sales.taxes.quiz.salestaxes.config.SalesTaxesConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.lastminute.sales.taxes.quiz.input.BasketMockUtil.basket1;
import static com.lastminute.sales.taxes.quiz.input.BasketMockUtil.basket1WithTaxes;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {SalesTaxesConfig.class})
@Configuration
public class SalesTaxesServiceTest {

    @Autowired
    private SalesTaxesService salesTaxesService;

    @Test
    public void applyTaxesToBasket1(){
        assertApplyTaxesToBasket( basket1WithTaxes(), basket1());
    }

    private void assertApplyTaxesToBasket( Basket exBasketWithTaxes, Basket basket){
        Basket basketWithTaxes = salesTaxesService.applyTaxes(basket);
        List<BasketItem> basketItems = basketWithTaxes.getBasketItems();
        List<BasketItem> exTaxesBasketItems = exBasketWithTaxes.getBasketItems();
        assertEquals( exTaxesBasketItems.size(), basketItems.size());
        for( int i=0; i< basketItems.size(); i++ ){
            assertEquals(exTaxesBasketItems.get(i).getPrice(), basketItems.get(i).getPrice());
        }
    }

}
