package com.lastminute.taxesquiz.sale.product.model;

import com.lastminute.taxesquiz.language.domain.GenericDomain;

import java.io.Serializable;
import java.util.Set;

public class GenericProduct extends GenericDomain implements Serializable {

    protected Set<ProductCategory> categories;

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ProductCategory> categories) {
        this.categories = categories;
    }

    public GenericProduct code(String code){
        this.code = code;
        return this;
    }

    public GenericProduct description(String description){
        this.description = description;
        return this;
    }

    public GenericProduct categories(Set<ProductCategory> categories){
        this.categories = categories;
        return this;
    }

}
