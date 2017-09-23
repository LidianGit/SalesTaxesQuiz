package com.lastminute.taxesquiz.sale.language.parser;

import com.lastminute.taxesquiz.sale.language.parser.exception.ParserException;

public interface Parser<I,O> {

    O parse(I input) throws ParserException;

}