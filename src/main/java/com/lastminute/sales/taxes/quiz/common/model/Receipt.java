package com.lastminute.sales.taxes.quiz.common.model;

import com.lastminute.sales.taxes.quiz.basket.model.Basket;

import java.math.BigDecimal;

public class Receipt {

    private Basket basket;
    private BigDecimal totalSalesTaxes;
    private BigDecimal total;

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public BigDecimal getTotalSalesTaxes() {
        return totalSalesTaxes;
    }

    public void setTotalSalesTaxes(BigDecimal totalSalesTaxes) {
        this.totalSalesTaxes = totalSalesTaxes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return basket + "\n"
        + "Sales Taxes: " + totalSalesTaxes + "\n"
        + "Total: " + total;
    }
}
