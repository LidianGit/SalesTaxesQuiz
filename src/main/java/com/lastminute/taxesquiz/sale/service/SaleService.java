package com.lastminute.taxesquiz.sale.service;

import com.lastminute.taxesquiz.sale.service.exception.SaleServiceException;

public interface SaleService {

    void processSale(String inputPath, String outputPath, String outputFileName) throws SaleServiceException;

}
