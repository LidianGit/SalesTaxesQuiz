package com.lastminute.taxesquiz.sale.language.domain;

import java.util.HashSet;
import java.util.Set;

public class Preposition {

    private static final Set<String> DOMAIN = new HashSet(60);

    static
    {
        DOMAIN.add("aboard");
        DOMAIN.add("about");
        DOMAIN.add("above");
        DOMAIN.add("across");
        DOMAIN.add("after");
        DOMAIN.add("against");
        DOMAIN.add("around");
        DOMAIN.add("along");
        DOMAIN.add("alongside");
        DOMAIN.add("among");
        DOMAIN.add("at");
        DOMAIN.add("athwart");
        DOMAIN.add("atop");
        DOMAIN.add("before");
        DOMAIN.add("behind");
        DOMAIN.add("below");
        DOMAIN.add("beneath");
        DOMAIN.add("beside");
        DOMAIN.add("besides");
        DOMAIN.add("between");
        DOMAIN.add("beyond");
        DOMAIN.add("but");
        DOMAIN.add("by");
        DOMAIN.add("despite");
        DOMAIN.add("down");
        DOMAIN.add("during");
        DOMAIN.add("except");
        DOMAIN.add("for");
        DOMAIN.add("from");
        DOMAIN.add("in");
        DOMAIN.add("inside");
        DOMAIN.add("into");
        DOMAIN.add("like");
        DOMAIN.add("near");
        DOMAIN.add("notwithstanding");
        DOMAIN.add("of");
        DOMAIN.add("off");
        DOMAIN.add("on");
        DOMAIN.add("opposite");
        DOMAIN.add("out");
        DOMAIN.add("outside");
        DOMAIN.add("over");
        DOMAIN.add("past");
        DOMAIN.add("round");
        DOMAIN.add("since");
        DOMAIN.add("through");
        DOMAIN.add("throughout");
        DOMAIN.add("till");
        DOMAIN.add("to");
        DOMAIN.add("toward");
        DOMAIN.add("under");
        DOMAIN.add("underneath");
        DOMAIN.add("until");
        DOMAIN.add("up");
        DOMAIN.add("with");
        DOMAIN.add("without");
        DOMAIN.add("within");
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