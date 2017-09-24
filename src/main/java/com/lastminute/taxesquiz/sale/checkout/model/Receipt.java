package com.lastminute.taxesquiz.sale.checkout.model;

import com.lastminute.taxesquiz.sale.basket.model.Basket;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Formattable;
import java.util.Formatter;

public class Receipt implements Formattable, Serializable{

    private Basket basket;
    private BigDecimal totalTaxes;
    private BigDecimal total;

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Receipt basket(Basket basket){
        this.basket = basket;
        return this;
    }

    public Receipt totalTaxes(BigDecimal totalTaxes){
        this.totalTaxes = totalTaxes;
        return this;
    }

    public Receipt total(BigDecimal total){
        this.total = total;
        return this;
    }

    @Override
    public String toString() {
        return basket + "\n"
        + "Sales Taxes: " + totalTaxes + "\n"
        + "Total: " + total;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        StringBuilder sb = new StringBuilder();
        Formatter basketFormatter = new Formatter();
        basketFormatter.format("%s", basket);
        sb.append(basketFormatter)
          .append("Sales Taxes:")
          .append(StringUtils.SPACE)
          .append(totalTaxes)
          .append(System.lineSeparator())
          .append("Total:")
          .append(StringUtils.SPACE)
          .append(total);
        formatter.format(sb.toString());
    }
}
