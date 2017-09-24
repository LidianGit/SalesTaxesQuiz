package com.lastminute.taxesquiz.sale.basket.parser;

import com.lastminute.taxesquiz.file.reader.service.FileReaderService;
import com.lastminute.taxesquiz.file.reader.service.exception.FileReaderServiceException;
import com.lastminute.taxesquiz.sale.basket.item.model.BasketItem;
import com.lastminute.taxesquiz.sale.basket.item.parser.BasketItemParser;
import com.lastminute.taxesquiz.language.parser.Parser;
import com.lastminute.taxesquiz.language.parser.exception.ParserException;
import com.lastminute.taxesquiz.sale.basket.model.Basket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasketParser implements Parser<String,Basket> {

    private static final Logger logger = LoggerFactory.getLogger(BasketParser.class);

    @Autowired
    FileReaderService fileReaderService;

    @Autowired
    BasketItemParser basketItemParser;

    @Override
    public Basket parse(String input) throws ParserException {
        Basket basket = new Basket();
        List<String> basketItemLines = null;
        try {
            basketItemLines = fileReaderService.lines(input);
        } catch (FileReaderServiceException e) {
            throw new ParserException(e);
        }
        List<BasketItem> basketItems = basketItemLines.stream()
                .map(this::parseBasketItem).collect(Collectors.toList());
        return basket.basketItems(basketItems);
    }

    private BasketItem parseBasketItem(String line){
        try {
            return basketItemParser.parse(line);
        } catch (ParserException e) {
            logger.error("error parsing basket item line [{}]", e);
        }
        return null;
    }

}
