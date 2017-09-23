package com.lastminute.taxesquiz.sale.order.basket.model;

import com.lastminute.taxesquiz.sale.order.basket.item.model.BasketItem;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Basket implements Formattable, Serializable{

    private static final long serialVersionUID = -6965168913310506566L;

    private List<BasketItem> basketItems;

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public Basket basketItems(List<BasketItem> basketItems){
        this.basketItems = basketItems;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        return new EqualsBuilder()
                .append(basketItems, basket.basketItems)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(basketItems)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("basketItems", basketItems)
                .toString();
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        StringBuilder sb = new StringBuilder();
        Formatter biFormatter = new Formatter();
        basketItems.forEach(
                bi -> sb.append(formatBasketItem(bi)).append(System.lineSeparator())
        );
        formatter.format(sb.toString());
    }

    private String formatBasketItem(BasketItem basketItem){
        Formatter biFormatter = new Formatter();
        biFormatter.format("%s", basketItem);
        return biFormatter.toString();
    }
}
