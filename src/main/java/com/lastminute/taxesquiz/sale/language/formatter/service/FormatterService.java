package com.lastminute.taxesquiz.sale.language.formatter.service;

import org.springframework.stereotype.Service;

import java.util.Formattable;
import java.util.Formatter;

@Service
public class FormatterService {

    public String format(Formattable formattable) {
        Formatter fmt = new Formatter();
        fmt.format("%s", formattable);
        return fmt.toString();
    }

}
