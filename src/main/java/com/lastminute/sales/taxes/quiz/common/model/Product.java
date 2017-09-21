package com.lastminute.sales.taxes.quiz.common.model;

import java.io.Serializable;
import java.util.Set;

public class Product implements Serializable {

    private static final long serialVersionUID = -7570056540194524800L;

    private String code;
    private String description;
    private Set<ProductCategory> categories;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ProductCategory> categories) {
        this.categories = categories;
    }

    public Product code(String code){
        this.code = code;
        return this;
    }

    public Product description(String description){
        this.description = description;
        return this;
    }

    public Product categories(Set<ProductCategory> categories){
        this.categories = categories;
        return this;
    }
}
