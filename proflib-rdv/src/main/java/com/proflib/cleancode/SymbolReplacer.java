package com.proflib.cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolReplacer {

        private String stringToReplace;
        private final Matcher symbolMatcher;
        protected List<String> alreadyReplaced = new ArrayList<String>();
        Pattern symbolPattern = Pattern.compile("\\$([a-zA-Z]\\w*)");

        SymbolReplacer(String stringtoReplace)
        {
            this.stringToReplace = stringtoReplace;
            this.symbolMatcher = symbolPattern.matcher(this.stringToReplace);
        }

        void replaceAllIfEligible() {
            while (symbolMatcher.find()) {
                String symbolName = symbolMatcher.group(1);
                replaceIfEligible(symbolName);
            }
        }

        private void replaceIfEligible(String symbolName) {
            if (!isMustBeReplaced(symbolName)) return;
            addToAlreadyListAndDoReplacement(symbolName);
        }

        private void addToAlreadyListAndDoReplacement(String symbolName) {
            alreadyReplaced.add(symbolName);
           replaceInStringToReplace(symbolName,translate(symbolName));
        }

        private void replaceInStringToReplace(String symbolName, String translatedSymbol) {
            stringToReplace= stringToReplace.replace("$" + symbolName, translatedSymbol);
        }

        private boolean isMustBeReplaced(String symbolName) {
            return getSymbol(symbolName) != null && !alreadyReplaced.contains(symbolName);
        }

        protected String translate(String symbolName) {
            return getSymbol(symbolName);
        }

        private String  getSymbol(String symbolName) {
        return "false";
    }
}
