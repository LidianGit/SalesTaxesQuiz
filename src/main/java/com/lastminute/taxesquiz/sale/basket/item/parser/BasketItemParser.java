package com.lastminute.taxesquiz.sale.basket.item.parser;

import com.lastminute.taxesquiz.sale.basket.item.model.BasketItem;
import com.lastminute.taxesquiz.language.parser.exception.ParserException;
import com.lastminute.taxesquiz.language.parser.GenericStringParser;
import com.lastminute.taxesquiz.sale.product.model.Product;
import com.lastminute.taxesquiz.sale.product.parser.ProductParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class BasketItemParser extends GenericStringParser<BasketItem> {

    private static final Logger logger = LoggerFactory.getLogger(BasketItemParser.class);

    @Autowired
    private ProductParser productParser;

    @Override
    protected LinkedList<String> init(String input){
        String[] inputWords = input.split(" ");
        LinkedList<String> words = new LinkedList<>(Arrays.asList(inputWords));
        return words;
    }

    @Override
    public BasketItem parse(LinkedList<String> words) throws ParserException {
        BasketItem basketItem = new BasketItem();
        try {
            //words = purify(words);
            Integer qty = Integer.parseInt(words.removeFirst());   // O(1)
            BigDecimal price = new BigDecimal(words.removeLast()); // O(1)
            words.removeLast();// O(1) remote at

            String productWord = StringUtils.join(words, " ");
            Product product = productParser.parse(productWord);
            basketItem.product(product).qty(qty).price(price);
        }catch (Exception e){
            logger.error("error parsing basket item words [ " + words + "]", e);
            throw new ParserException(e);
        }
        return basketItem;
    }

    /*
    private LinkedList<String> purify(LinkedList<String> words){
        Iterator<String> stringIterator = words.iterator();
        while(stringIterator.hasNext()){
            String word = stringIterator.next();
            if(Preposition.lookup(word)){
                stringIterator.remove();
                continue;
            }
            if(ProductPackage.lookup((word))){
                stringIterator.remove();
                continue;
            }
        }
        return words;
    }
    */
}
