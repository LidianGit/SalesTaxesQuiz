package com.lastminute.sales.taxes.quiz.common.model;

import java.util.List;

public class ProductCategory {

    private String code;
    private String description;
    private ProductMacroCategory macroCategory;
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

    public ProductMacroCategory getMacroCategory() {
        return macroCategory;
    }

    public void setMacroCategory(ProductMacroCategory macroCategory) {
        this.macroCategory = macroCategory;
    }

    public List<SaleTax> getSaleTaxes() {
        return saleTaxes;
    }

    public void setSaleTaxes(List<SaleTax> saleTaxes) {
        this.saleTaxes = saleTaxes;
    }

    public ProductCategory code(String code){
        this.code = code;
        return this;
    }

    public ProductCategory description(String description){
        this.description = description;
        return this;
    }

    public ProductCategory macroCategory(ProductMacroCategory macroCategory){
        this.macroCategory = macroCategory;
        return this;
    }

    public ProductCategory saleTaxes(List<SaleTax> saleTaxes){
        this.saleTaxes = saleTaxes;
        return this;
    }

}
