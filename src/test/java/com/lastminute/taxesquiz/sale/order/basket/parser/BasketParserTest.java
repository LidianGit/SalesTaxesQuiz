package com.lastminute.taxesquiz.sale.order.basket.parser;

import com.lastminute.taxesquiz.file.reader.service.FileReaderService;
import com.lastminute.taxesquiz.sale.language.parser.exception.ParserException;
import com.lastminute.taxesquiz.sale.order.basket.model.Basket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static com.lastminute.taxesquiz.util.BasketMockUtil.basket1;
import static com.lastminute.taxesquiz.util.BasketMockUtil.basket2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {BasketParserTest.class})
@Configuration
@ComponentScan({"com.lastminute.taxesquiz.sale.order.basket", "com.lastminute.taxesquiz.sale.product.parser"})
public class BasketParserTest {

    @Autowired
    BasketParser basketParser;

    @Autowired
    FileReaderService fileReaderService;

    private void parseBasketTest(Basket basketExp, List<String> expectedInputLines ){
        try {
            when(fileReaderService.lines("/mockFilePath")).thenReturn(
                    expectedInputLines
            );
            Basket basket = basketParser.parse("/mockFilePath");
            assertEquals(basketExp, basket);
        } catch (ParserException e) {
            fail();
        }
    }

    @Test
    public void parseBasket1Test(){
        parseBasketTest(basket1(), Arrays.asList("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85") );
    }

    @Test
    public void parseBasket2Test(){
        parseBasketTest(basket2(), Arrays.asList("1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50") );
    }

    @Bean
    public FileReaderService fileReaderService(){
        return mock(FileReaderService.class);
    }

}
