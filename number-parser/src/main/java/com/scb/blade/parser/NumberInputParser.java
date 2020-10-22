package com.scb.blade.parser;

import com.scb.blade.matcher.NumberMatcher;
import com.scb.blade.parser.rules.NumberLineParserRules;
import com.scb.blade.reader.buffer.model.NumberLineInput;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NumberInputParser {

    private NumberMatcher numberMatcher;
    private NumberLineParserRules numberLineParserRules;

    public NumberInputParser(NumberLineParserRules numberLineParserRules, NumberMatcher numberMatcher) {
        this.numberMatcher = numberMatcher;
        this.numberLineParserRules = numberLineParserRules;
    }

    public ParsingResult parse(NumberLineInput numberLineInput) {
        List<Optional<Integer>> digitMatches = numberLineInput.getDigitInputs()
                .stream()
                .map(this.numberMatcher::getNumber)
                .collect(Collectors.toList());

        return new ParsingResult(digitMatches, this.numberLineParserRules.valid(numberLineInput));
    }
}
