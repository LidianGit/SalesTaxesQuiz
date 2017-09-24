package com.lastminute.taxesquiz.test.language.domain.service.impl;

import com.lastminute.taxesquiz.language.domain.DomainType;
import com.lastminute.taxesquiz.language.domain.GenericDomain;
import com.lastminute.taxesquiz.language.domain.service.impl.JsonDomainService;
import com.lastminute.taxesquiz.sale.product.model.GenericProduct;
import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import com.lastminute.taxesquiz.test.common.PropertiesTestConfig;
import com.lastminute.taxesquiz.test.language.domain.service.impl.config.JsonDomainServiceTestConfig;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Map;

import static com.lastminute.taxesquiz.sale.product.ProductConstants.DEFAULT_CAT_KEY_WORD;
import static com.lastminute.taxesquiz.util.MockUtil.defaultProductCategory;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {JsonDomainServiceTestConfig.class, PropertiesTestConfig.class})
public class JsonDomainServiceTest {

    @Autowired
    private JsonDomainService jsonDomainService;

    @Test
    public void initTest(){
        Map<DomainType, Map<String, ? extends GenericDomain>> domains = jsonDomainService.getDomains();
        assertFalse(domains.isEmpty());
        assertEquals(5, domains.get(DomainType.PRODUCT_CATEGORY).size());
        assertEquals(5, domains.get(DomainType.PRODUCT).size());
        assertEquals(3, domains.get(DomainType.PRODUCT_PACKAGE).size());
        assertEquals(57, domains.get(DomainType.PREPOSITION).size());
    }

    @Test
    public void retrieveGenericDomainTest(){
        ProductCategory productCategory = jsonDomainService.retrieveGenericDomain(DomainType.PRODUCT_CATEGORY, DEFAULT_CAT_KEY_WORD, "equals");
        assertEquals(defaultProductCategory(), productCategory);

        GenericProduct genericProduct = jsonDomainService.retrieveGenericDomain(DomainType.PRODUCT, "chocolates", "isContained");
        assertEquals(jsonDomainService.getDomains().get(DomainType.PRODUCT).get("chocolate"), genericProduct );
    }

    @Test(expected = NotImplementedException.class)
    public void retrieveGenericDomainNotImplementedTest(){
        jsonDomainService.retrieveGenericDomain(DomainType.PRODUCT, "chocolate", "contains");
    }

    @Test
    public void retrieveAllGenericDomainTest(){
        Collection<GenericDomain> productPackaging = jsonDomainService.retrieveAllGenericDomain(DomainType.PRODUCT_PACKAGE);

        assertThat(productPackaging, hasItems(
                new GenericDomain().code("box"),
                new GenericDomain().code("bottle"),
                new GenericDomain().code("packet")
                )
        );

    }

}
