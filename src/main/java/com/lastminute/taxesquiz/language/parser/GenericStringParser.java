package com.lastminute.taxesquiz.language.parser;

import com.lastminute.taxesquiz.language.parser.exception.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public abstract class GenericStringParser<O> implements Parser<String,O> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public O parse(String input) throws ParserException {
        logger.info("start parsing - input [{}]", input);
        O output = parse(init(input));
        logger.info("end parsing - output [{}]", output);
        return output;
    }

    protected abstract LinkedList<String> init(String input);

    protected abstract O parse(LinkedList<String> words) throws ParserException;

}
