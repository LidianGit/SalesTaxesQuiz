package com.lastminute.taxesquiz.sale.product.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Set;

public class Product implements Serializable {

    private static final long serialVersionUID = -7570056540194524800L;

    private String code;
    private String description;
    private Boolean imported = false;
    private String packaging="";
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

    public Boolean getImported() {
        return imported;
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public Product code(String code){
        this.code = code;
        return this;
    }

    public Product description(String description){
        this.description = description;
        return this;
    }

    public Product packaging(String packaging){
        this.packaging = packaging;
        return this;
    }

    public Product imported(Boolean imported){
        this.imported = imported;
        return this;
    }

    public Product categories(Set<ProductCategory> categories){
        this.categories = categories;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(code, product.code)
                .append(description, product.description)
                .append(imported, product.imported)
                .append(packaging, product.packaging)
                .append(categories, product.categories)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .append(description)
                .append(imported)
                .append(packaging)
                .append(categories)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("description", description)
                .append("imported", imported)
                .append("packaging", packaging)
                .toString();
    }
}
