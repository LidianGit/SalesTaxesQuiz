package com.lastminute.sales.taxes.quiz.basket.model;

import com.lastminute.sales.taxes.quiz.basket.item.model.BasketItem;

import java.io.Serializable;
import java.util.List;

public class Basket implements Serializable{

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

}
