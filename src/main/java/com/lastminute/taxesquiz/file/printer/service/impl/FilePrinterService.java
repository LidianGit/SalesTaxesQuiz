package com.lastminute.taxesquiz.file.printer.service.impl;

import com.lastminute.taxesquiz.file.writer.service.FileWriterService;
import com.lastminute.taxesquiz.file.printer.service.PrinterService;
import com.lastminute.taxesquiz.language.formatter.service.FormatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Formattable;

@Service
public class FilePrinterService implements PrinterService {

    @Autowired
    private FormatterService formatterService;

    @Autowired
    private FileWriterService fileWriterService;

    @Override
    public void print(Object object, String path, String fileName) {
        StringWriter sw = new StringWriter();
        sw.write(object.toString());
        fileWriterService.write(path, fileName, sw);
    }

    @Override
    public void prettyPrint(Formattable formattable, String path, String fileName) {
        String formattedReceipt = formatterService.format(formattable);
        print(formattedReceipt, path, fileName);
    }


}
