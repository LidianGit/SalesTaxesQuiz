package com.lastminute.sales.taxes.quiz.basket.item.parser;

import com.lastminute.sales.taxes.quiz.basket.item.model.BasketItem;
import com.lastminute.sales.taxes.quiz.common.model.ProductCategory;
import com.lastminute.sales.taxes.quiz.language.parser.exception.ParserException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.lastminute.sales.taxes.quiz.util.BasketMockUtil.*;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

public class BasketItemParserTest {

    private BasketItemParser basketItemParser;

    @Before
    public void setUp(){
        basketItemParser = new BasketItemParser();
    }

    /*

        1 imported bottle of perfume at 27.99
        1 bottle of perfume at 18.99
        1 packet of headache pills at 9.75
        1 box of imported chocolates at 11.25
    */
    @Test
    public void parseBasket1(){
        try {
            //ITEM 1
            String input = "1 book at 12.49";
            BasketItem basketItem = basketItemParser.parse(input);
            BasketItem basketItemExp = basketItem(bookProduct("book", false), new BigDecimal("12.49"), new Integer(1));

            assertEquals( basketItemExp.getPrice(), basketItem.getPrice());
            assertEquals( basketItemExp.getQty(), basketItem.getQty());
            assertEquals( basketItemExp.getProduct().getCode(), basketItem.getProduct().getCode());
            assertEquals( basketItemExp.getProduct().getDescription(), basketItem.getProduct().getDescription());
            assertEquals( basketItemExp.getProduct().getCategories().size(), basketItem.getProduct().getCategories().size());
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));

            assertEquals("books", basketItem.getProduct().getCategories().iterator().next().getCode() );
            assertEquals("books", basketItem.getProduct().getCategories().iterator().next().getDescription() );
            assertTrue(basketItem.getProduct().getCategories().iterator().next().getTaxes().isEmpty() );

            //ITEM 2
            input = "1 music CD at 14.99";
            basketItem = basketItemParser.parse(input);
            basketItemExp = basketItem(defaultTaxableProduct("music CD", false), new BigDecimal("14.99"), new Integer(1));

            assertEquals( basketItemExp.getPrice(), basketItem.getPrice());
            assertEquals( basketItemExp.getQty(), basketItem.getQty());
            assertEquals( basketItemExp.getProduct().getCode(), basketItem.getProduct().getCode());
            assertEquals( basketItemExp.getProduct().getDescription(), basketItem.getProduct().getDescription());
            assertEquals( basketItemExp.getProduct().getCategories().size(), basketItem.getProduct().getCategories().size());
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));

            ProductCategory productCategory = basketItem.getProduct().getCategories().iterator().next();
            assertEquals("default", productCategory.getCode() );
            assertEquals("default", productCategory.getDescription() );
            assertThat( productCategory.getTaxes(), hasSize(1));
            assertEquals("10.00", productCategory.getTaxes().get(0).getPercentage().toString());

            //ITEM 3
            input = "1 chocolate bar at 0.85";
            basketItem = basketItemParser.parse(input);
            basketItemExp = basketItem(foodProduct("chocolate bar", false), new BigDecimal("0.85"), new Integer(1));

            assertEquals( basketItemExp.getPrice(), basketItem.getPrice());
            assertEquals( basketItemExp.getQty(), basketItem.getQty());
            assertEquals( basketItemExp.getProduct().getCode(), basketItem.getProduct().getCode());
            assertEquals( basketItemExp.getProduct().getDescription(), basketItem.getProduct().getDescription());
            assertEquals( basketItemExp.getProduct().getCategories().size(), basketItem.getProduct().getCategories().size());
            assertThat( basketItem.getProduct().getCategories(), hasSize(1));

            assertEquals("food", basketItem.getProduct().getCategories().iterator().next().getCode() );
            assertEquals("food", basketItem.getProduct().getCategories().iterator().next().getDescription() );
            assertTrue(basketItem.getProduct().getCategories().iterator().next().getTaxes().isEmpty() );
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
            BasketItem basketItemExp = basketItem(foodProduct("chocolates", true), new BigDecimal("10.00"), new Integer(1));

            assertEquals( basketItemExp.getPrice(), basketItem.getPrice());
            assertEquals( basketItemExp.getQty(), basketItem.getQty());
            assertEquals( basketItemExp.getProduct().getCode(), basketItem.getProduct().getCode());
            assertEquals( basketItemExp.getProduct().getDescription(), basketItem.getProduct().getDescription());
            assertEquals( basketItemExp.getProduct().getCategories().size(), basketItem.getProduct().getCategories().size());
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));

            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    new ProductCategory().code("food").description("food"),
                    importedProductCategory()
                    )
            );

            //ITEM 2
            input = "1 imported bottle of perfume at 47.50";
            basketItem = basketItemParser.parse(input);
            basketItemExp = basketItem(defaultTaxableProduct("perfume", true), new BigDecimal("47.50"), new Integer(1));

            assertEquals( basketItemExp.getPrice(), basketItem.getPrice());
            assertEquals( basketItemExp.getQty(), basketItem.getQty());
            assertEquals( basketItemExp.getProduct().getCode(), basketItem.getProduct().getCode());
            assertEquals( basketItemExp.getProduct().getDescription(), basketItem.getProduct().getDescription());
            assertEquals( basketItemExp.getProduct().getCategories().size(), basketItem.getProduct().getCategories().size());
            assertThat( basketItem.getProduct().getCategories(), hasSize(2));

            assertThat(basketItem.getProduct().getCategories(), hasItems(
                    defaultProductCategory(),
                    importedProductCategory()
                    )
            );

        } catch (ParserException e) {
            fail();
        }
    }





//    @Test
//    public void parseBasket2(){
//        try {
//            String input = "1 book at 12.49";
//            BasketItem basketItem = basketItemParser.parse(input);
//            BasketItem basketItemExp = basketItem(bookProduct("book", false), new BigDecimal("12.49"), new Integer(1));
//
//            assertEquals( basketItemExp.getPrice(), basketItem.getPrice());
//            assertEquals( basketItemExp.getQty(), basketItem.getQty());
//            assertEquals( basketItemExp.getProduct().getCode(), basketItem.getProduct().getCode());
//            assertEquals( basketItemExp.getProduct().getDescription(), basketItem.getProduct().getDescription());
//            assertEquals( basketItemExp.getProduct().getCategories().size(), basketItem.getProduct().getCategories().size());
//            assertThat( basketItem.getProduct().getCategories(), hasSize(1));
//
//            assertEquals("books", basketItem.getProduct().getCategories().iterator().next().getCode() );
//            assertEquals("books", basketItem.getProduct().getCategories().iterator().next().getDescription() );
//            assertTrue(basketItem.getProduct().getCategories().iterator().next().getTaxes().isEmpty() );
//        } catch (ParserException e) {
//            fail();
//        }
//    }


}
