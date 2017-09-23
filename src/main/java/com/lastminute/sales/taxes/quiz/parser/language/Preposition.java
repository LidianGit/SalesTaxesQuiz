package com.lastminute.sales.taxes.quiz.parser.language;

import java.util.HashSet;

public class Preposition {

    private static final HashSet<String> PREPOSITIONS = new HashSet(60);;

    static
    {
        PREPOSITIONS.add("aboard");
        PREPOSITIONS.add("about");
        PREPOSITIONS.add("above");
        PREPOSITIONS.add("across");
        PREPOSITIONS.add("after");
        PREPOSITIONS.add("against");
        PREPOSITIONS.add("around");
        PREPOSITIONS.add("along");
        PREPOSITIONS.add("alongside");
        PREPOSITIONS.add("among");
        PREPOSITIONS.add("at");
        PREPOSITIONS.add("athwart");
        PREPOSITIONS.add("atop");
        PREPOSITIONS.add("before");
        PREPOSITIONS.add("behind");
        PREPOSITIONS.add("below");
        PREPOSITIONS.add("beneath");
        PREPOSITIONS.add("beside");
        PREPOSITIONS.add("besides");
        PREPOSITIONS.add("between");
        PREPOSITIONS.add("beyond");
        PREPOSITIONS.add("but");
        PREPOSITIONS.add("by");
        PREPOSITIONS.add("despite");
        PREPOSITIONS.add("down");
        PREPOSITIONS.add("during");
        PREPOSITIONS.add("except");
        PREPOSITIONS.add("for");
        PREPOSITIONS.add("from");
        PREPOSITIONS.add("in");
        PREPOSITIONS.add("inside");
        PREPOSITIONS.add("into");
        PREPOSITIONS.add("like");
        PREPOSITIONS.add("near");
        PREPOSITIONS.add("notwithstanding");
        PREPOSITIONS.add("of");
        PREPOSITIONS.add("off");
        PREPOSITIONS.add("on");
        PREPOSITIONS.add("opposite");
        PREPOSITIONS.add("out");
        PREPOSITIONS.add("outside");
        PREPOSITIONS.add("over");
        PREPOSITIONS.add("past");
        PREPOSITIONS.add("round");
        PREPOSITIONS.add("since");
        PREPOSITIONS.add("through");
        PREPOSITIONS.add("throughout");
        PREPOSITIONS.add("till");
        PREPOSITIONS.add("to");
        PREPOSITIONS.add("toward");
        PREPOSITIONS.add("under");
        PREPOSITIONS.add("underneath");
        PREPOSITIONS.add("until");
        PREPOSITIONS.add("up");
        PREPOSITIONS.add("with");
        PREPOSITIONS.add("without");
        PREPOSITIONS.add("within");
    }

    /**
     * Looks up a word in the english preposition dictionary.
     *
     * @param word the word to look up
     * @return true, if it is a preposition
     */
    public static boolean lookup(String word) {
        return PREPOSITIONS.contains(word);
    }
}