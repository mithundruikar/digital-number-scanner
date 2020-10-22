package com.scb.blade.parser;

import com.scb.blade.matcher.NumberMatcher;
import com.scb.blade.parser.rules.NumberLineParserRules;
import com.scb.blade.reader.buffer.model.NumberLineInput;

public class NumberInputParser {
    private NumberMatcher numberMatcher;
    public NumberInputParser(NumberLineParserRules numberLineParserRules, NumberMatcher numberMatcher) {
        this.numberMatcher = numberMatcher;
    }

    public String parse(NumberLineInput numberLineInput) {
        return null;
    }
}
