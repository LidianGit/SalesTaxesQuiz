package com.lastminute.sales.taxes.quiz.common.model;

import com.lastminute.sales.taxes.quiz.tax.model.Tax;

import java.util.List;

public class ProductMacroCategory {

    private String code;
    private String description;
    private List<Tax> taxes;

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
}
