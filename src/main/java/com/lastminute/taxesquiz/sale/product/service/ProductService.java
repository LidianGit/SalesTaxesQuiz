package com.lastminute.taxesquiz.sale.product.service;

import com.lastminute.taxesquiz.sale.product.model.Product;

public interface ProductService {

    Product retrieveProduct(String productName, String packaging, boolean imported);

}
