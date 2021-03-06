package com.lastminute.taxesquiz.test.sale.order.basket.parser;

import com.lastminute.taxesquiz.file.reader.service.FileReaderService;
import com.lastminute.taxesquiz.file.reader.service.exception.FileReaderServiceException;
import com.lastminute.taxesquiz.language.domain.config.DomainConfig;
import com.lastminute.taxesquiz.language.parser.exception.ParserException;
import com.lastminute.taxesquiz.sale.basket.config.BasketConfig;
import com.lastminute.taxesquiz.sale.basket.model.Basket;
import com.lastminute.taxesquiz.sale.basket.parser.BasketParser;
import com.lastminute.taxesquiz.sale.product.config.ProductConfig;
import com.lastminute.taxesquiz.test.common.PropertiesTestConfig;
import com.lastminute.taxesquiz.test.sale.order.basket.parser.config.BasketParserTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static com.lastminute.taxesquiz.util.MockUtil.basket1;
import static com.lastminute.taxesquiz.util.MockUtil.basket2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {BasketParserTestConfig.class, PropertiesTestConfig.class, BasketConfig.class, ProductConfig.class, DomainConfig.class})
public class BasketParserFuncTest {

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
        } catch (ParserException | FileReaderServiceException e) {
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

}
