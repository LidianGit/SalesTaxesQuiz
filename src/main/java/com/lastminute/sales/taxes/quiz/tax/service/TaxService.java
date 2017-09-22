package com.lastminute.sales.taxes.quiz.tax.service;

import com.lastminute.sales.taxes.quiz.basket.model.Basket;

public interface TaxService {

    Basket applyTaxes(Basket basket);

}
