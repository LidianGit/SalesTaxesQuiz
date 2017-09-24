package com.lastminute.taxesquiz.file.reader.service.exception;

public class FileReaderServiceException extends Exception {

    public FileReaderServiceException() {
    }

    public FileReaderServiceException(String message) {
        super(message);
    }

    public FileReaderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReaderServiceException(Throwable cause) {
        super(cause);
    }

    public FileReaderServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
