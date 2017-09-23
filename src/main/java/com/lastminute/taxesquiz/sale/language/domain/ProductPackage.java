package com.lastminute.taxesquiz.sale.language.domain;

import java.util.HashSet;
import java.util.Set;

public class ProductPackage {

    private static final Set<String> DOMAIN = new HashSet(3);

    static
    {
        DOMAIN.add("box");
        DOMAIN.add("bottle");
        DOMAIN.add("packet");
    }

    /**
     * Looks up a word in the english preposition dictionary.
     *
     * @param word the word to look up
     * @return true, if it is a preposition
     */
    public static boolean lookup(String word) {
        return DOMAIN.contains(word);
    }

}
