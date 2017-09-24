package com.lastminute.taxesquiz.sale.product.model;

import com.lastminute.taxesquiz.language.domain.GenericDomain;
import com.lastminute.taxesquiz.sale.tax.model.Tax;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends GenericDomain {

    private static final long serialVersionUID = -7889839740720675768L;

    private List<Tax> taxes = new ArrayList<>();

    public List<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Tax> taxes) {
        this.taxes = taxes;
    }

    public ProductCategory code(String code){
        this.code = code;
        return this;
    }

    public ProductCategory description(String description){
        this.description = description;
        return this;
    }

    public ProductCategory taxes(List<Tax> taxes){
        this.taxes = taxes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProductCategory that = (ProductCategory) o;

        return new EqualsBuilder()
                .append(code, that.code)
                .append(description, that.description)
                .append(taxes, that.taxes)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .append(description)
                .append(taxes)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("description", description)
                .append("taxes", taxes)
                .toString();
    }
}
