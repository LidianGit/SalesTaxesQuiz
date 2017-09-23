package com.lastminute.taxesquiz.sale.tax.service;

import com.lastminute.taxesquiz.sale.order.basket.model.Basket;

public interface TaxService {

    Basket applyTaxes(Basket basket);

}
