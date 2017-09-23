package com.lastminute.taxesquiz.sale.product.parser;

import com.lastminute.taxesquiz.sale.language.parser.exception.ParserException;
import com.lastminute.taxesquiz.sale.language.parser.GenericStringParser;
import com.lastminute.taxesquiz.sale.product.model.Product;
import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import com.lastminute.taxesquiz.sale.tax.model.Tax;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class ProductParser extends GenericStringParser<Product> {

    private static final String IMPORTED_CAT_KEY_WORD = "imported";
    private static final String DEFAULT_CAT_KEY_WORD = "default";

    private static final String IMPORTED_TAX_KEY = "IMPORTED_TAX";
    private static final String DEFAULT_TAX_KEY = "DEFAULT_TAX";

    private static final Map<String, Tax> taxTypes = new HashMap<>();
    private static final Map<String, ProductCategory> productCategoryDictionary = new HashMap<>();
    private static final Map<String,String> productToCategoryDictionary = new HashMap<>();
    private static final List<String> productKeyWordList = Arrays.asList("book", "chocolate", "headache pills");

    static {
        taxTypes.put(IMPORTED_TAX_KEY, new Tax().percent(new BigDecimal("5.00")) );
        taxTypes.put(DEFAULT_TAX_KEY, new Tax().percent(new BigDecimal("10.00")) );

        List<Tax> defaultTax = new ArrayList<>();
        defaultTax.add(taxTypes.get(DEFAULT_TAX_KEY));

        List<Tax> importedTax = new ArrayList<>();
        importedTax.add(taxTypes.get(IMPORTED_TAX_KEY));

        productCategoryDictionary.put(DEFAULT_CAT_KEY_WORD, new ProductCategory().code(DEFAULT_CAT_KEY_WORD).description(DEFAULT_CAT_KEY_WORD).taxes(defaultTax));
        productCategoryDictionary.put(IMPORTED_CAT_KEY_WORD, new ProductCategory().code(IMPORTED_CAT_KEY_WORD).description(IMPORTED_CAT_KEY_WORD).taxes(importedTax));

        //untaxableProducts: books, food, and medical products
        productCategoryDictionary.put("books", new ProductCategory().code("books").description("books"));
        productCategoryDictionary.put("food", new ProductCategory().code("food").description("food"));
        productCategoryDictionary.put("medical", new ProductCategory().code("medical").description("medical"));

        productToCategoryDictionary.put("book", "books");
        productToCategoryDictionary.put("chocolate", "food");
        productToCategoryDictionary.put("headache pills", "medical");
    }

    @Override
    protected LinkedList<String> init(String input) {
        String[] inputWords = input.split(" ");
        LinkedList<String> words = new LinkedList<>(Arrays.asList(inputWords));
        return words;
    }

    @Override
    protected Product parse(LinkedList<String> words) throws ParserException {
        Boolean imported = findImported(words);
        String productWord = StringUtils.join(words, " ");
        Set<ProductCategory> productCategories = new HashSet<>();
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
        return new Product().code(productWord).description(productWord).imported(imported).categories(productCategories);
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
