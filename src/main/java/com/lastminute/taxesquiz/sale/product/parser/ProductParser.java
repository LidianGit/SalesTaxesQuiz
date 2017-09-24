package com.lastminute.taxesquiz.sale.product.parser;

import com.lastminute.taxesquiz.language.domain.DomainType;
import com.lastminute.taxesquiz.language.domain.service.DomainService;
import com.lastminute.taxesquiz.language.parser.GenericStringParser;
import com.lastminute.taxesquiz.language.parser.exception.ParserException;
import com.lastminute.taxesquiz.sale.product.model.Product;
import com.lastminute.taxesquiz.sale.product.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import static com.lastminute.taxesquiz.sale.product.ProductConstants.IMPORTED_CAT_KEY_WORD;

@Component
public class ProductParser extends GenericStringParser<Product> {

    private static final Logger logger = LoggerFactory.getLogger(ProductParser.class);

    @Autowired
    private DomainService domainService;

    @Autowired
    private ProductService productService;


    @Override
    protected LinkedList<String> init(String input) {
        String[] inputWords = input.split(" ");
        return new LinkedList<>(Arrays.asList(inputWords));
    }

    @Override
    protected Product parse(LinkedList<String> words) throws ParserException {
        Product product;
        try{
            String packaging = findPackaging(words);
            Boolean imported = findImported(words);
            String productWord = StringUtils.join(words, " ");
            product = productService.retrieveProduct(productWord, packaging, imported);
        }catch (Exception e){
            logger.error("Unable to parse product words [" + words + "]", e);
            throw new ParserException(e);
        }
        return product;
    }

    private void appendPackagingWord(StringBuilder packagingBuilder, String word){
        if(packagingBuilder.length() != 0) packagingBuilder.append(" ");
        packagingBuilder.append(word);
    }

    private String findPackaging(LinkedList<String> words){
        Iterator<String> stringIterator = words.iterator();
        StringBuilder packagingBuilder = new StringBuilder();
        while(stringIterator.hasNext()){
            String word = stringIterator.next();
            if(domainService.contains(DomainType.PREPOSITION, word)
            || domainService.contains(DomainType.PRODUCT_PACKAGE, word)){
                appendPackagingWord(packagingBuilder, word);
                stringIterator.remove();
            }
        }
        return packagingBuilder.toString();
    }

    private Boolean findImported(LinkedList<String> words){
        Iterator<String> stringIterator = words.iterator();
        while(stringIterator.hasNext()){
            String word = stringIterator.next();
            if(IMPORTED_CAT_KEY_WORD.equals(word)){
                stringIterator.remove(); // O(1)
                return true;
            }
        }
        return false;
    }

}
