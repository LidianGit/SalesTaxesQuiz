package com.lastminute.sales.taxes.quiz.basket.item.parser;

import com.lastminute.sales.taxes.quiz.basket.item.model.BasketItem;
import com.lastminute.sales.taxes.quiz.common.model.Product;
import com.lastminute.sales.taxes.quiz.common.model.ProductCategory;
import com.lastminute.sales.taxes.quiz.language.parser.exception.ParserException;
import com.lastminute.sales.taxes.quiz.parser.language.Preposition;
import com.lastminute.sales.taxes.quiz.tax.model.Tax;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class BasketItemParser {

    Logger logger = LoggerFactory.getLogger(BasketItemParser.class);

    private static final String IMPORTED_CAT_KEY_WORD = "imported";
    private static final String DEFAULT_CAT_KEY_WORD = "default";

    private static final String IMPORTED_TAX_KEY = "IMPORTED_TAX";
    private static final String DEFAULT_TAX_KEY = "DEFAULT_TAX";

    private static final List<String> packageKeyWordList = Arrays.asList("box", "bottle", "packet");

    private static final List<String> productKeyWordList = Arrays.asList("book", "chocolate", "headache pills");

    private static final Map<String, Tax> taxTypes = new HashMap<>();
    private static final Map<String, ProductCategory> productCategoryDictionary = new HashMap<>();
    private static final Map<String,String> productToCategoryDictionary = new HashMap<>();


    static {
        taxTypes.put(IMPORTED_TAX_KEY, new Tax().percent(new BigDecimal("5.00")) );
        taxTypes.put(DEFAULT_TAX_KEY, new Tax().percent(new BigDecimal("10.00")) );

        List<Tax> defaultTax = new ArrayList<>();
        defaultTax.add(taxTypes.get(DEFAULT_TAX_KEY));

        List<Tax> importedTax = new ArrayList<>();
        importedTax.add(taxTypes.get(IMPORTED_TAX_KEY));

        productCategoryDictionary.put(DEFAULT_CAT_KEY_WORD, new ProductCategory().code(DEFAULT_CAT_KEY_WORD).description(DEFAULT_CAT_KEY_WORD).saleTaxes(defaultTax));
        productCategoryDictionary.put(IMPORTED_CAT_KEY_WORD, new ProductCategory().code(IMPORTED_CAT_KEY_WORD).description(IMPORTED_CAT_KEY_WORD).saleTaxes(importedTax));

        //untaxableProducts: books, food, and medical products
        productCategoryDictionary.put("books", new ProductCategory().code("books").description("books"));
        productCategoryDictionary.put("food", new ProductCategory().code("food").description("food"));
        productCategoryDictionary.put("medical", new ProductCategory().code("medical").description("medical"));

        productToCategoryDictionary.put("book", "books");
        productToCategoryDictionary.put("chocolate", "food");
        productToCategoryDictionary.put("headache pills", "medical");
    }

    public BasketItem parse(String input) throws ParserException {
        BasketItem basketItem = new BasketItem();
        try {
            logger.info("start parsing basket item [{}]", input);
            String[] inputWords = input.split(" ");
            String packaging = "";
            boolean imported = false;
            LinkedList<String> words = new LinkedList<>(Arrays.asList(inputWords));
            String qty = words.removeFirst();
            String price = words.removeLast();

            Iterator<String> stringIterator = words.iterator();
            while(stringIterator.hasNext()){
                String word = stringIterator.next();
                if(Preposition.lookup(word)){
                    stringIterator.remove();
                    continue;
                }
                if(packageKeyWordList.contains(word)){
                    packaging = word;
                    stringIterator.remove();
                    continue;
                }
                if(IMPORTED_CAT_KEY_WORD.equals(word)){
                    imported = true;
                    stringIterator.remove();
                }
            }

            String productWord = StringUtils.join(words, " ");
            Set<ProductCategory> productCategories = new HashSet<>();
            Product product = new Product().code(productWord).description(productWord).categories(productCategories);
            Optional<String> knownProduct = productKeyWordList.stream().filter(productWord::contains).findAny();
            if( knownProduct.isPresent() ){
                productCategories.add(productCategoryDictionary.get(productToCategoryDictionary.get(knownProduct.get())));
            }else{
                productCategories.add(productCategoryDictionary.get(DEFAULT_CAT_KEY_WORD));
            }
            if(imported){
                ProductCategory importedCategory = productCategoryDictionary.get(IMPORTED_CAT_KEY_WORD);
                productCategories.add(importedCategory);
            }
            basketItem.product(product).qty(Integer.parseInt(qty)).price(new BigDecimal(price));
        }catch (Exception e){
            logger.error("error parsing basket item [ " + input + "]", e);
            throw new ParserException(e);
        }
        logger.info("end parsing basket item [{}]", basketItem);
        return basketItem;
    }


}
