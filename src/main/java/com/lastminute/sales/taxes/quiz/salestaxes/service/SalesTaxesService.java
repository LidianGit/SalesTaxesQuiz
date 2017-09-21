package com.lastminute.sales.taxes.quiz.salestaxes.service;

import com.lastminute.sales.taxes.quiz.common.model.Basket;

public interface SalesTaxesService {

    Basket applyTaxes(Basket basket);

}
