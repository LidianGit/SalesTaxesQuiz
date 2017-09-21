package com.lastminute.sales.taxes.quiz.common.model;

import java.math.BigDecimal;

public class SaleTax {

    private BigDecimal percent;

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public SaleTax percent(BigDecimal percent){
        this.percent = percent;
        return this;
    }

}
