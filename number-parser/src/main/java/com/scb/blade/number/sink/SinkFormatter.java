package com.scb.blade.number.sink;

import com.scb.blade.parser.ParsingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * It formats incoming input line into required format of string represetation
 */
public class SinkFormatter {
    public static final String INVALID_NUMBER_PLACEHOLDER = "?";
    public static final String INVALID_NUMBER_LINE_SUFFIX = "ILL";

    public String format(ParsingResult parsingResult) {
        boolean noFullMatch = parsingResult.getDigitMatches().contains(Optional.empty());
        List<String> matchedResults = parsingResult.getDigitMatches().stream()
                .map(matchResult -> matchResult.isPresent() ? matchResult.get().toString() : INVALID_NUMBER_PLACEHOLDER)
                .collect(Collectors.toList());
        String numberString = String.join("", matchedResults);
        if((noFullMatch || !parsingResult.isAsPerRules())) {
            numberString += INVALID_NUMBER_LINE_SUFFIX;
        }
        return numberString;
    }

}
