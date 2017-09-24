package com.lastminute.taxesquiz.sale.product.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Set;

public class Product extends GenericProduct implements Formattable, Serializable {

    private static final long serialVersionUID = -7570056540194524800L;

    private Boolean imported = false;
    private String packaging="";

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

    public Product categories(Set<ProductCategory> categories){
        this.categories = categories;
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

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        StringBuilder sb = new StringBuilder();
        if(imported){
            sb.append("imported");
            sb.append(StringUtils.SPACE);
        }
        if(!packaging.isEmpty()){
            sb.append(packaging);
            sb.append(StringUtils.SPACE);
        }
        sb.append(description);
        formatter.format(sb.toString());
    }
}
