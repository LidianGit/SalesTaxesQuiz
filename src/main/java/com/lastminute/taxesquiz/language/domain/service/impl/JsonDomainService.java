package com.lastminute.taxesquiz.language.domain.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lastminute.taxesquiz.language.domain.DomainType;
import com.lastminute.taxesquiz.language.domain.GenericDomain;
import com.lastminute.taxesquiz.language.domain.service.DomainService;
import com.lastminute.taxesquiz.sale.product.model.GenericProduct;
import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class JsonDomainService implements DomainService{

    private static final Logger logger = LoggerFactory.getLogger(JsonDomainService.class);

    private final Map<DomainType, Map<String, ? extends GenericDomain>> domains = new HashMap<>();

    @Value("${domains.product.categories.file}")
    private String productCategoriesFile;

    @Value("${domains.product.file}")
    private String productFile;

    @Value("${domains.preposition.file}")
    private String prepositionFile;

    @Value("${domains.product.packaging.file}")
    private String productPackagingFile;

    @PostConstruct
    private void init(){
        try {
            loadGenericDomains(productCategoriesFile, DomainType.PRODUCT_CATEGORY, new TypeToken<List<ProductCategory>>(){}.getType(), ProductCategory.class);
            loadGenericDomains(productFile, DomainType.PRODUCT, new TypeToken<List<GenericProduct>>(){}.getType(), GenericProduct.class);
            loadGenericDomains(prepositionFile, DomainType.PREPOSITION, new TypeToken<List<GenericDomain>>(){}.getType(), GenericDomain.class);
            loadGenericDomains(productPackagingFile, DomainType.PRODUCT_PACKAGE, new TypeToken<List<GenericDomain>>(){}.getType(), GenericDomain.class);
        } catch (FileNotFoundException e) {
            logger.error("error loading domain from json files: ", e);
        }
    }

    private <T extends GenericDomain> void loadGenericDomains(String domainFile, DomainType domainType, Type type, Class<T> domainClass ) throws FileNotFoundException {
        logger.debug("loading domain [{}]", domainType.name());
        Gson gson = new Gson();
        final Map<String, T> genericDomainMap = new HashMap<>();
        List<T> domain = gson.fromJson(new FileReader(domainFile), type);
        domain.forEach(
                domainEntry -> genericDomainMap.put(domainEntry.getCode(), domainEntry)
        );
        domains.put(domainType, genericDomainMap);
        logger.debug("loeaded domain [{}]", domainType.name());
    }

    @Override
    public <T extends GenericDomain> Collection<T> retrieveAllGenericDomain(DomainType domainType) {
        return (Collection<T>) domains.get(domainType).values();
    }

    @Override
    public <T extends GenericDomain> T retrieveGenericDomain(DomainType domainType, String code, String policy) {
        switch (policy){
            case "equals": {
                return (T) domains.get(domainType).get(code);
            }
            case "isContained": {
                Map<String, ? extends GenericDomain> domain = domains.get(domainType);
                Optional<? extends GenericDomain> genericDomain = domain.values()
                        .stream()
                        .filter(o -> code.contains(o.getCode()))
                        .findAny();
                return (T) genericDomain.get();
            }
            default:{
                throw new NotImplementedException("domain retrieve policy not implemented");
            }
        }
    }

    @Override
    public boolean contains(DomainType domainType, String code) {
        return domains.get(domainType).containsKey(code);
    }

    public Map<DomainType, Map<String, ? extends GenericDomain>> getDomains() {
        return domains;
    }
}
