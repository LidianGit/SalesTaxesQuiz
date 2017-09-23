package com.lastminute.sales.taxes.quiz.common.model;

import com.lastminute.sales.taxes.quiz.tax.model.Tax;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {

    private String code;
    private String description;
    private List<Tax> taxes = new ArrayList<>();

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

    public ProductCategory saleTaxes(List<Tax> taxes){
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
