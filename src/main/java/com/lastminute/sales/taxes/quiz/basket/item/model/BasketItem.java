package com.lastminute.sales.taxes.quiz.basket.item.model;

import com.lastminute.sales.taxes.quiz.common.model.Product;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

public class BasketItem implements Serializable {

    private static final long serialVersionUID = 241529658779770112L;

    private Product product;
    private BigDecimal price;
    private Integer qty;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BasketItem product(Product product){
        this.product = product;
        return this;
    }

    public BasketItem price(BigDecimal price){
        this.price = price;
        return this;
    }

    public BasketItem qty(Integer qty){
        this.qty = qty;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("product", product)
                .append("price", price)
                .append("qty", qty)
                .toString();
    }
}
