package com.lastminute.taxesquiz.sale.order.basket.item.parser;

import com.lastminute.taxesquiz.sale.order.basket.item.model.BasketItem;
import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import com.lastminute.taxesquiz.sale.language.parser.exception.ParserException;
import com.lastminute.taxesquiz.util.BasketMockUtil;
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
@ContextConfiguration( classes = {BasketItemParserTest.class})
@Configuration
@ComponentScan({"com.lastminute.taxesquiz.sale.order.basket.item.parser", "com.lastminute.taxesquiz.sale.product.parser"})
public class BasketItemParserTest {

    @Autowired
    private BasketItemParser basketItemParser;

    @Test
    public void parseBasket1(){
        try {
            //ITEM 1
            String input = "1 book at 12.49";
            BasketItem basketItem = basketItemParser.parse(input);
            BasketItem basketItemExp = BasketMockUtil.basketItem(
                    BasketMockUtil.bookProduct("book", "",false), new BigDecimal("12.49"), new Integer(1));
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("books").description("books")
                    )
            );

            //ITEM 2
            input = "1 music CD at 14.99";
            basketItem = basketItemParser.parse(input);
            basketItemExp = BasketMockUtil.basketItem(
                    BasketMockUtil.defaultTaxableProduct("music CD", "",false), new BigDecimal("14.99"), new Integer(1));
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
            assertThat(basketItem.getProduct().getCategories(), Matchers.hasItems(
                    BasketMockUtil.defaultProductCategory()
                    )
            );

            //ITEM 3
            input = "1 chocolate bar at 0.85";
            basketItem = basketItemParser.parse(input);
            basketItemExp = BasketMockUtil.basketItem(
                    BasketMockUtil.foodProduct("chocolate bar", "", false), new BigDecimal("0.85"), new Integer(1));
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
            BasketItem basketItemExp = BasketMockUtil.basketItem(
                    BasketMockUtil.foodProduct("chocolates", "box of", true), new BigDecimal("10.00"), new Integer(1));
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));
            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("food").description("food"),
                    BasketMockUtil.importedProductCategory()
                    )
            );

            //ITEM 2
            input = "1 imported bottle of perfume at 47.50";
            basketItem = basketItemParser.parse(input);
            basketItemExp = BasketMockUtil.basketItem(
                    BasketMockUtil.defaultTaxableProduct("perfume", "bottle of", true), new BigDecimal("47.50"), new Integer(1));
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));
            assertThat(basketItem.getProduct().getCategories(), Matchers.hasItems(
                    BasketMockUtil.defaultProductCategory(),
                    BasketMockUtil.importedProductCategory()
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
            BasketItem basketItemExp = BasketMockUtil.basketItem(
                    BasketMockUtil.defaultTaxableProduct("perfume", "bottle of", true), new BigDecimal("27.99"), new Integer(1));
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));
            assertThat(basketItem.getProduct().getCategories(), Matchers.hasItems(
                    BasketMockUtil.defaultProductCategory(),
                    BasketMockUtil.importedProductCategory()
                    )
            );

            //ITEM 2
            input = "1 bottle of perfume at 18.99";
            basketItem = basketItemParser.parse(input);
            basketItemExp = BasketMockUtil.basketItem(
                    BasketMockUtil.defaultTaxableProduct("perfume", "bottle of", false), new BigDecimal("18.99"), new Integer(1));
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
            assertThat(basketItem.getProduct().getCategories(), Matchers.hasItems(
                    BasketMockUtil.defaultProductCategory()
                    )
            );

            //ITEM 3
            input = "1 packet of headache pills at 9.75";
            basketItem = basketItemParser.parse(input);
            basketItemExp = BasketMockUtil.basketItem(
                    BasketMockUtil.medicalProduct("headache pills", "packet of", false), new BigDecimal("9.75"), new Integer(1));
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("medical").description("medical")
                    )
            );

            //ITEM 4
            input = "1 box of imported chocolates at 11.25";
            basketItem = basketItemParser.parse(input);
            basketItemExp = BasketMockUtil.basketItem(
                    BasketMockUtil.foodProduct("chocolates", "box of", true), new BigDecimal("11.25"), new Integer(1));
            assertEquals(basketItemExp, basketItem);
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));
            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("food").description("food"),
                    BasketMockUtil.importedProductCategory()
                    )
            );

        } catch (ParserException e) {
            fail();
        }
    }

/*
    private static void assertEqualsBasketItem( BasketItem basketItemExp, BasketItem basketItem ){
        assertEquals( basketItemExp.getPrice(), basketItem.getPrice());
        assertEquals( basketItemExp.getQty(), basketItem.getQty());
        assertEquals( basketItemExp.getProduct().getCode(), basketItem.getProduct().getCode());
        assertEquals( basketItemExp.getProduct().getDescription(), basketItem.getProduct().getDescription());
        assertEquals( basketItemExp.getProduct().getCategories(), basketItem.getProduct().getCategories());
    }
*/

}
