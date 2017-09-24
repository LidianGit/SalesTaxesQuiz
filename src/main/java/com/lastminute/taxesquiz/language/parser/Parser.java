package com.lastminute.taxesquiz.language.parser;

import com.lastminute.taxesquiz.language.parser.exception.ParserException;

public interface Parser<I,O> {

    O parse(I input) throws ParserException;

}