package com.lastminute.sales.taxes.quiz.tax.model;

import java.math.BigDecimal;

public class Tax {

    private BigDecimal percentage;

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public Tax percent(BigDecimal percent){
        this.percentage = percent;
        return this;
    }

}
