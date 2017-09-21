package com.lastminute.sales.taxes.quiz.common.model;

import java.util.List;

public class ProductMacroCategory {

    private String code;
    private String description;
    private List<SaleTax> saleTaxes;

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

    public List<SaleTax> getSaleTaxes() {
        return saleTaxes;
    }

    public void setSaleTaxes(List<SaleTax> saleTaxes) {
        this.saleTaxes = saleTaxes;
    }
}
