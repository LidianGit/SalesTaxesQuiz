package com.lastminute.taxesquiz.sale.service.exception;

public class SaleServiceException extends Exception {

    public SaleServiceException() {
    }

    public SaleServiceException(String message) {
        super(message);
    }

    public SaleServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaleServiceException(Throwable cause) {
        super(cause);
    }

    public SaleServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
