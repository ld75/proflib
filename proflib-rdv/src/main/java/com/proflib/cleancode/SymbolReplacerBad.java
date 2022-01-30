package com.proflib.cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolReplacerBad {
        protected String stringToReplace;
        protected List<String> alreadyReplaced = new ArrayList<String>();

        SymbolReplacerBad(String s) {
            this.stringToReplace = s;
        }

        String replace() {
            Pattern symbolPattern = Pattern.compile("\\$([a-zA-Z]\\w*)");
            Matcher symbolMatcher = symbolPattern.matcher(stringToReplace);
            while (symbolMatcher.find()) {
                String symbolName = symbolMatcher.group(1);
                if (getSymbol(symbolName) != null && !alreadyReplaced.contains(symbolName)) {
                    alreadyReplaced.add(symbolName);
                    stringToReplace = stringToReplace.replace("$" + symbolName, translate(symbolName));
                }
            }
            return stringToReplace;
        }

    private String getSymbol(String symbolName) {
        return "false";
    }

    protected String translate(String symbolName) {
            return getSymbol(symbolName);
        }
    }

