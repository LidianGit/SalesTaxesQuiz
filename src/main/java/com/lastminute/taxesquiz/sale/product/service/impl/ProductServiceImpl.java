package com.lastminute.taxesquiz.sale.product.service.impl;

import com.lastminute.taxesquiz.language.domain.DomainType;
import com.lastminute.taxesquiz.language.domain.service.DomainService;
import com.lastminute.taxesquiz.sale.product.model.GenericProduct;
import com.lastminute.taxesquiz.sale.product.model.Product;
import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import com.lastminute.taxesquiz.sale.product.service.ProductService;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.lastminute.taxesquiz.sale.product.ProductConstants.DEFAULT_CAT_KEY_WORD;
import static com.lastminute.taxesquiz.sale.product.ProductConstants.IMPORTED_CAT_KEY_WORD;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private DomainService domainService;

    public Product retrieveProduct(String productCode, String packaging, boolean imported){
        logger.info("start retrieving product [{} - {} - {}]", productCode, packaging, imported);
        GenericProduct genericProduct = domainService.retrieveGenericDomain(DomainType.PRODUCT, productCode, "isContained");
        if(genericProduct == null){
            logger.warn("product [{}] not found, retrieving as default", productCode, packaging, imported);
            Set<ProductCategory> productCategories = new HashSet<>();
            ProductCategory productCategory = domainService.retrieveGenericDomain(DomainType.PRODUCT_CATEGORY, DEFAULT_CAT_KEY_WORD, "equals");
            productCategories.add(productCategory);
            genericProduct = new GenericProduct().code(productCode).description(productCode).categories(productCategories);
        }
        Product product = new Product().imported(imported).packaging(packaging);
        BeanUtils.copyProperties(SerializationUtils.clone(genericProduct), product);
        product.description(productCode);
        if(imported){
            ProductCategory importedCategory = domainService.retrieveGenericDomain(DomainType.PRODUCT_CATEGORY, IMPORTED_CAT_KEY_WORD, "equals");
            product.getCategories().add(importedCategory);
        }
        logger.info("retrieved product [{}]", product);
        return product;
    }

}
