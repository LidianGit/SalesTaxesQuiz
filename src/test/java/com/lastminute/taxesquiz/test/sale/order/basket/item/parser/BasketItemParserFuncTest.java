package com.lastminute.taxesquiz.test.sale.order.basket.item.parser;

import com.lastminute.taxesquiz.language.domain.config.DomainConfig;
import com.lastminute.taxesquiz.sale.basket.item.model.BasketItem;
import com.lastminute.taxesquiz.sale.basket.item.parser.BasketItemParser;
import com.lastminute.taxesquiz.sale.product.config.ProductConfig;
import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import com.lastminute.taxesquiz.language.parser.exception.ParserException;
import com.lastminute.taxesquiz.test.common.PropertiesTestConfig;
import com.lastminute.taxesquiz.util.MockUtil;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {BasketItemParserFuncTest.class, PropertiesTestConfig.class, ProductConfig.class, DomainConfig.class})
@Configuration
@ComponentScan({"com.lastminute.taxesquiz.sale.basket.item.parser"})
public class BasketItemParserFuncTest {

    @Autowired
    private BasketItemParser basketItemParser;

    @Test
    public void parseBasket1(){
        try {
            //ITEM 1
            String input = "1 book at 12.49";
            BasketItem basketItem = basketItemParser.parse(input);
            BasketItem basketItemExp = MockUtil.basketItem(
                    MockUtil.bookProduct("book","book", "",false), new BigDecimal("12.49"), 1);
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("books").description("books")
                    )
            );

            //ITEM 2
            input = "1 music CD at 14.99";
            basketItem = basketItemParser.parse(input);
            basketItemExp = MockUtil.basketItem(
                    MockUtil.defaultTaxableProduct("music CD","music CD", "",false), new BigDecimal("14.99"), 1);
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
            assertThat(basketItem.getProduct().getCategories(), Matchers.hasItems(
                    MockUtil.defaultProductCategory()
                    )
            );

            //ITEM 3
            input = "1 chocolate bar at 0.85";
            basketItem = basketItemParser.parse(input);
            basketItemExp = MockUtil.basketItem(
                    MockUtil.foodProduct("chocolate","chocolate bar", "", false), new BigDecimal("0.85"), 1);
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("food").description("food")
                    )
            );

        } catch (ParserException e) {
            fail();
        }
    }


    @Test
    public void parseBasket2() {
        try {
            //ITEM 1
            String input = "1 imported box of chocolates at 10.00";
            BasketItem basketItem = basketItemParser.parse(input);
            BasketItem basketItemExp = MockUtil.basketItem(
                    MockUtil.foodProduct("chocolate","chocolates", "box of", true), new BigDecimal("10.00"), 1);
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));
            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("food").description("food"),
                    MockUtil.importedProductCategory()
                    )
            );

            //ITEM 2
            input = "1 imported bottle of perfume at 47.50";
            basketItem = basketItemParser.parse(input);
            basketItemExp = MockUtil.basketItem(
                    MockUtil.defaultTaxableProduct("perfume","perfume", "bottle of", true), new BigDecimal("47.50"), 1);
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));
            assertThat(basketItem.getProduct().getCategories(), Matchers.hasItems(
                    MockUtil.defaultProductCategory(),
                    MockUtil.importedProductCategory()
                    )
            );

        } catch (ParserException e) {
            fail();
        }
    }

    @Test
    public void parseBasket3() {
        try {
            //ITEM 1
            String input = "1 imported bottle of perfume at 27.99";
            BasketItem basketItem = basketItemParser.parse(input);
            BasketItem basketItemExp = MockUtil.basketItem(
                    MockUtil.defaultTaxableProduct("perfume","perfume", "bottle of", true), new BigDecimal("27.99"), 1);
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));
            assertThat(basketItem.getProduct().getCategories(), Matchers.hasItems(
                    MockUtil.defaultProductCategory(),
                    MockUtil.importedProductCategory()
                    )
            );

            //ITEM 2
            input = "1 bottle of perfume at 18.99";
            basketItem = basketItemParser.parse(input);
            basketItemExp = MockUtil.basketItem(
                    MockUtil.defaultTaxableProduct("perfume","perfume", "bottle of", false), new BigDecimal("18.99"), 1);
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
            assertThat(basketItem.getProduct().getCategories(), Matchers.hasItems(
                    MockUtil.defaultProductCategory()
                    )
            );

            //ITEM 3
            input = "1 packet of headache pills at 9.75";
            basketItem = basketItemParser.parse(input);
            basketItemExp = MockUtil.basketItem(
                    MockUtil.medicalProduct("headache pills","headache pills", "packet of", false), new BigDecimal("9.75"), 1);
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("medical").description("medical")
                    )
            );

            //ITEM 4
            input = "1 box of imported chocolates at 11.25";
            basketItem = basketItemParser.parse(input);
            basketItemExp = MockUtil.basketItem(
                    MockUtil.foodProduct("chocolate","chocolates", "box of", true), new BigDecimal("11.25"), 1);
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));
            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("food").description("food"),
                    MockUtil.importedProductCategory()
                    )
            );

        } catch (ParserException e) {
            fail();
        }
    }

}
