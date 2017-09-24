package com.lastminute.taxesquiz.test.sale.product.service;

import com.lastminute.taxesquiz.language.domain.DomainType;
import com.lastminute.taxesquiz.language.domain.service.DomainService;
import com.lastminute.taxesquiz.sale.product.model.GenericProduct;
import com.lastminute.taxesquiz.sale.product.model.Product;
import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import com.lastminute.taxesquiz.sale.product.service.ProductService;
import com.lastminute.taxesquiz.test.sale.product.service.config.ProductServiceTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static com.lastminute.taxesquiz.sale.product.ProductConstants.DEFAULT_CAT_KEY_WORD;
import static com.lastminute.taxesquiz.sale.product.ProductConstants.IMPORTED_CAT_KEY_WORD;
import static com.lastminute.taxesquiz.util.MockUtil.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {ProductServiceTestConfig.class})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private DomainService domainService;

    @Test
    public void retrieveProductTest(){
        when(domainService.retrieveGenericDomain(DomainType.PRODUCT_CATEGORY, IMPORTED_CAT_KEY_WORD, "equals"))
                .thenReturn(importedProductCategory());
        when(domainService.retrieveGenericDomain(DomainType.PRODUCT_CATEGORY, DEFAULT_CAT_KEY_WORD, "equals"))
                .thenReturn(defaultProductCategory());

        //existing product untaxable not imported
        Set<ProductCategory> categories = new HashSet<>();
        categories.add(new ProductCategory().code("food").description("food"));
        when(domainService.retrieveGenericDomain(DomainType.PRODUCT, "banana", "isContained"))
                .thenReturn(new GenericProduct().code("banana").description("banana").categories(categories));
        Product product = productService.retrieveProduct("banana", "test", false);
        assertEquals(foodProduct("banana","banana", "test", false), product);

        //existing product untaxable imported
        when(domainService.retrieveGenericDomain(DomainType.PRODUCT, "banana", "isContained"))
                .thenReturn(new GenericProduct().code("banana").description("banana").categories(categories));
        product = productService.retrieveProduct("banana", "test", true);
        assertEquals(foodProduct("banana","banana", "test", true), product);

        //unknown product not imported
        when(domainService.retrieveGenericDomain(DomainType.PRODUCT, "test", "isContained"))
                .thenReturn(null);
        product = productService.retrieveProduct("test", "test", false);
        assertEquals(defaultTaxableProduct("test","test", "test", false), product);

        //unknown product imported
        when(domainService.retrieveGenericDomain(DomainType.PRODUCT, "test", "isContained"))
                .thenReturn(null);
        product = productService.retrieveProduct("test", "test", true);
        assertEquals(defaultTaxableProduct("test", "test", "test", true), product);
    }

}
