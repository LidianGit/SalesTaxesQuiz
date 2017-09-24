package com.lastminute.taxesquiz.language.formatter.service;

import org.springframework.stereotype.Service;

import java.util.Formattable;
import java.util.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FormatterService {

    private static final Logger logger = LoggerFactory.getLogger(FormatterService.class);

    public String format(Formattable formattable) {
        logger.info("start to format [{}]", formattable);
        Formatter fmt = new Formatter();
        fmt.format("%s", formattable);
        logger.info("formatted in [{}]", fmt);
        return fmt.toString();
    }

}
