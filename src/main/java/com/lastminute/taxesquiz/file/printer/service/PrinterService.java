package com.lastminute.taxesquiz.file.printer.service;

import java.util.Formattable;

public interface PrinterService {

    void print(Object object, String path, String fileName);

    void prettyPrint(Formattable formattable, String path, String fileName);

}
