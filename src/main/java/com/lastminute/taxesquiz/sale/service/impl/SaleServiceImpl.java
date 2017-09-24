package com.lastminute.taxesquiz.sale.service.impl;

import com.lastminute.taxesquiz.file.printer.service.PrinterService;
import com.lastminute.taxesquiz.sale.basket.model.Basket;
import com.lastminute.taxesquiz.sale.basket.parser.BasketParser;
import com.lastminute.taxesquiz.sale.checkout.model.Receipt;
import com.lastminute.taxesquiz.sale.checkout.service.CheckoutService;
import com.lastminute.taxesquiz.language.parser.exception.ParserException;
import com.lastminute.taxesquiz.sale.service.SaleService;
import com.lastminute.taxesquiz.sale.service.exception.SaleServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SaleServiceImpl implements SaleService{

    private static final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);

    @Autowired
    private BasketParser basketParser;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private PrinterService printerService;

    public void processSale(String inputPath, String outputPath, String outputFileName) throws SaleServiceException {
        File file = new File(inputPath);
        if( file.isDirectory() ){
            processMultiSale(file.list(), inputPath, outputPath, outputFileName);
            return;
        }
        processSingleSale(inputPath, outputPath, outputFileName);
    }

    private void processMultiSale(String[] inputPaths, String rootPath, String outputPath, String outputFileName) throws SaleServiceException {
        if( inputPaths != null){
            AtomicInteger atomicInteger = new AtomicInteger(1);
            Arrays.asList(inputPaths).forEach(
                    fileName -> {
                        try {
                            int i = atomicInteger.getAndIncrement();
                            String[] ofnTokenized = outputFileName.split("\\.(?=[^\\.]+$)");
                            String fn = ofnTokenized[0];
                            String fext= ofnTokenized[1];
                            String newOutputFileName = new StringBuilder().append(fn).append("_").append(i).append(".").append(fext).toString();
                            processSale(rootPath + File.separator + fileName, outputPath, newOutputFileName);
                        } catch (SaleServiceException e) {
                            logger.error("Error processing sale "+ rootPath + File.separator + fileName, e );
                        }
                    }
            );
        }
        logger.info("successfully processed all sales in [{}]", rootPath);
    }

    private void processSingleSale(String inputPath, String outputPath, String outputFileName) throws SaleServiceException {
        try {
            logger.info("start processing sale [{}]", inputPath);
            Basket basket = basketParser.parse(inputPath);
            Receipt receipt = checkoutService.issueReceipt(basket);
            printerService.prettyPrint(receipt, outputPath, outputFileName);
            logger.info("successfully processed sale [{}]", inputPath);
        } catch (ParserException e) {
            logger.error("Error processing sale [" + inputPath + "]", e );
            throw new SaleServiceException("Error processing sale [" + inputPath + "]", e);
        }
    }

}
